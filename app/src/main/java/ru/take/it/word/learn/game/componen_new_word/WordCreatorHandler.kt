package ru.take.it.word.learn.game.componen_new_word

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
import ru.take.it.word.learn.game.ui.kit.button_icon.ButtonIconItem
import ru.take.it.word.learn.game.ui.kit.field.TextFieldItem
import ru.take.it.word.learn.game.ui.kit.request.RequestItem

class WordCreatorHandler(
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

    val addTranslateValue: MutableValue<ButtonIconItem.State> = MutableValue(
        ButtonIconItem.State(
            id = "add_translate_id",
            icon = R.drawable.ic_plus,
            onClick = ::onClickAddTranslate
        )
    )

    private val requestStateSubscriber = requestState.subscribe { state ->
        saveValue.value = saveValue.value.copy(requestState = state)
    }

    private fun onChangeWord(id: String, value: String) {
        wordValue.value = wordValue.value.copy(text = value)
        saveValue.value = saveValue.value.copy(isEnabled = isSaveButtonEnabled(wordText = value))
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

    private fun onClickAddTranslate() {
        if (translateValue.value.size == 5) {
            return
        }
        translateValue.value = translateValue.value.toMutableList().apply {
            val index = translateValue.value.lastIndex + 1

            add(
                TextFieldItem.State(
                    id = "$TRANSLATE_ID${index}",
                    text = "",
                    supportText = null,
                    label = resManager.getString(R.string.create_word_translate_label_more),
                    onChangeValue = ::onChangeTranslate
                )
            )

            if (size == 5) {
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
                requestState.value = RequestItem.State.Idle
                dialogNavigation.dismiss()
            }.onFailure {

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
    }
}