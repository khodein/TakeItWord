package ru.take.it.word.learn.game.component_creator_word

import com.arkivanov.decompose.value.Value
import ru.take.it.word.learn.game.ui.kit.button.ButtonItem
import ru.take.it.word.learn.game.ui.kit.field.TextFieldItem

interface WordCreatorComponent {
    val wordValue: Value<TextFieldItem.State>
    val translateValue: Value<List<TextFieldItem.State>>
    val saveValue: Value<ButtonItem.State>
    val messageTopButtonValue: Value<Message>

    data class Message(
        val isError: Boolean = false,
        val isSuccess: Boolean = false,
        val message: String
    )

    fun onClickRemove(id: String)
    fun onClickAdd()

    interface Provider {
        fun onAddNewWord()
        fun onUpdateWord()
    }
}