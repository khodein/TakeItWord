package ru.take.it.word.learn.game.componen_new_word

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.take.it.word.learn.game.core.navigation.dialog.DialogNavigation
import ru.take.it.word.learn.game.repositories.word.WordModel
import ru.take.it.word.learn.game.repositories.word.WordRepository
import ru.take.it.word.learn.game.ui.kit.button.ButtonItem
import ru.take.it.word.learn.game.ui.kit.field.TextFieldItem
import ru.take.it.word.learn.game.ui.kit.request.RequestItem

class WordCreatorHandler(
    private val scope: CoroutineScope,
) : InstanceKeeper.Instance, KoinComponent {

    private val wordRepository by inject<WordRepository>()
    private val dialogNavigation by inject<DialogNavigation>()

    private val requestState: MutableValue<RequestItem.State> = MutableValue(RequestItem.State.Idle)

    val wordValue: MutableValue<TextFieldItem.State> = MutableValue(
        TextFieldItem.State(
            id = "word_id",
            text = "",
            label = "Слово",
            onChangeValue = ::onChangeWord
        )
    )

    val translateValue: MutableValue<TextFieldItem.State> = MutableValue(
        TextFieldItem.State(
            id = "translate_id",
            text = "",
            label = "Перевод",
            onChangeValue = ::onChangeTranslate
        )
    )

    val saveValue: MutableValue<ButtonItem.State> = MutableValue(
        ButtonItem.State(
            id = "save_id",
            value = "Сохранить",
            isEnabled = isSaveButtonEnabled(),
            onClick = ::onClickSave
        )
    )

    init {
        requestState.subscribe { state ->
            saveValue.value = saveValue.value.copy(requestState = state)
        }
    }

    private fun onChangeWord(value: String) {
        wordValue.value = wordValue.value.copy(text = value)
        saveValue.value = saveValue.value.copy(isEnabled = isSaveButtonEnabled(wordText = value))
    }

    private fun onChangeTranslate(value: String) {
        translateValue.value = translateValue.value.copy(text = value)
        saveValue.value =
            saveValue.value.copy(isEnabled = isSaveButtonEnabled(translateText = value))
    }

    private fun isSaveButtonEnabled(
        wordText: String = wordValue.value.text,
        translateText: String = translateValue.value.text,
    ): Boolean {
        return wordText.trim().isNotEmpty() && translateText.trim().isNotEmpty()
    }

    private fun onClickSave() {
        requestState.value = RequestItem.State.Loading()
        scope.launch {
            runCatching {
                withContext(Dispatchers.IO) {
                    delay(500)
                    wordRepository.setWordsList(
                        listOf(
                            WordModel(
                                value = wordValue.value.text,
                                translate = translateValue.value.text
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
}