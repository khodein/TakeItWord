package ru.take.it.word.learn.game.component_creator_word

import androidx.compose.ui.graphics.Color
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.take.it.word.learn.game.R
import ru.take.it.word.learn.game.core.navigation.dialog.DialogNavigation
import ru.take.it.word.learn.game.repositories.word.WordModel
import ru.take.it.word.learn.game.repositories.word.WordRepository
import ru.take.it.word.learn.game.tools.ResManager
import ru.take.it.word.learn.game.ui.kit.button.ButtonItem
import ru.take.it.word.learn.game.ui.kit.field.TextFieldItem
import ru.take.it.word.learn.game.ui.kit.request.RequestItem

class WordCreatorHandler(
    private val provider: WordCreatorComponent.Provider,
    private val scope: CoroutineScope,
) : InstanceKeeper.Instance, KoinComponent {

    private val wordRepository by inject<WordRepository>()
    private val dialogNavigation by inject<DialogNavigation>()
    private val resManager by inject<ResManager>()

    private val requestState: MutableValue<RequestItem.State> = MutableValue(RequestItem.State.Idle)

    private val translateSupportTextLast by lazy {
        resManager.getString(R.string.create_word_translate_support_text_last)
    }

    val wordValue: MutableValue<TextFieldItem.State> = MutableValue(
        TextFieldItem.State(
            id = "word_id",
            text = "",
            label = resManager.getString(R.string.create_word_label),
            supportText = resManager.getString(R.string.create_word_support),
            onChangeValue = ::onChangeWord
        )
    )

    val translateValue: MutableValue<List<TextFieldItem.State>> = MutableValue(
        listOf(
            TextFieldItem.State(
                id = MAIN_TRANSLATE_ID,
                text = "",
                label = resManager.getString(R.string.create_word_translate_label),
                supportText = resManager.getString(R.string.create_word_translate_support_text_first),
                onChangeValue = ::onChangeTranslate
            )
        )
    )

    val saveValue: MutableValue<ButtonItem.Default> = MutableValue(
        ButtonItem.Default(
            id = "save_id",
            value = resManager.getString(R.string.create_word_button_save),
            isEnabled = isSaveButtonEnabled(),
            onClick = ::onClickSave
        )
    )

    private val messageIdleValue = WordCreatorComponent.Message(
        isError = false,
        message = resManager.getString(R.string.create_word_bottom_notice)
    )

    val messageTopButtonValue: MutableValue<WordCreatorComponent.Message> =
        MutableValue(messageIdleValue)

    private val requestStateSubscriber = requestState.subscribe { state ->
        saveValue.value = saveValue.value.copy(requestState = state)
        messageTopButtonValue.value = when (state) {
            is RequestItem.State.Error -> messageIdleValue.copy(
                isError = true,
                message = state.message.orEmpty()
            )

            else -> messageIdleValue
        }
    }

    private fun onChangeWord(id: String, value: String) {
        wordValue.value = wordValue.value.copy(text = value)
        saveValue.value = saveValue.value.copy(isEnabled = isSaveButtonEnabled(wordText = value))
        requestState.value = RequestItem.State.Idle
    }

    private fun onChangeTranslate(id: String, value: String) {
        translateValue.value = translateValue.value.toMutableList().apply {
            val index = indexOfFirst { it.id == id }
            if (index != -1) {
                val item = get(index)
                val newItem = item.copy(text = value)
                set(index, newItem)
            }
        }

        if (id == MAIN_TRANSLATE_ID) {
            saveValue.value =
                saveValue.value.copy(isEnabled = isSaveButtonEnabled(translateText = value))
        }
        requestState.value = RequestItem.State.Idle
    }

    fun onClickRemoveTranslate(id: String) {
        translateValue.value = translateValue.value.toMutableList().apply {
            val indexSupport = indexOfFirst { it.supportText == translateSupportTextLast }
            if (indexSupport != -1) {
                val supportItem = get(indexSupport)
                val newSupportItem = supportItem.copy(supportText = null)
                set(indexSupport, newSupportItem)
            }

            val index = indexOfFirst { it.id == id }
            if (index != -1) {
                removeAt(index)
            }
        }
    }

    fun onClickAddTranslate() {
        translateValue.value = translateValue.value.toMutableList().apply {
            if (size == MAX_TRANSLATE_COUNT) {
                return
            }

            add(
                TextFieldItem.State(
                    id = "$TRANSLATE_ID${lastIndex + 1}",
                    text = "",
                    supportText = null,
                    label = resManager.getString(R.string.create_word_translate_label_more),
                    onChangeValue = ::onChangeTranslate
                )
            )

            if (size == MAX_TRANSLATE_COUNT) {
                val item = get(lastIndex)
                set(lastIndex, item.copy(supportText = translateSupportTextLast))
            }
        }
    }

    private fun isSaveButtonEnabled(
        wordText: String = wordValue.value.text,
        translateText: String = translateValue.value.firstOrNull()?.text ?: "",
    ): Boolean {
        return wordText.trim().isNotEmpty() && translateText.trim().isNotEmpty()
    }

    private fun onClickSave() {
        requestState.value = RequestItem.State.Loading(trackColor = Color.White)
        scope.launch {
            runCatching {
                withContext(Dispatchers.IO) {
                    delay(500)
                    val compositeTranslate = translateValue.value
                        .filter { it.text.isNotEmpty() }
                        .joinToString(",") { it.text }
                    wordRepository.setWordsList(
                        listOf(
                            WordModel(
                                value = wordValue.value.text.trim().lowercase(),
                                translate = compositeTranslate
                            )
                        )
                    )
                }
            }.onSuccess {
                requestState.value = RequestItem.State.Success
                provider.onAddNewWord()
                dialogNavigation.dismiss()
            }.onFailure {
                requestState.value = RequestItem.State.Error(
                    message = resManager.getString(R.string.create_word_translate_error)
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        requestStateSubscriber.cancel()
        scope.cancel()
    }

    private companion object {
        const val TRANSLATE_ID = "translate_id"
        const val MAIN_TRANSLATE_ID = "${TRANSLATE_ID}${0}"
        const val MAX_TRANSLATE_COUNT = 5
    }
}