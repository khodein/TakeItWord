package ru.take.it.word.learn.game.componen_new_word

import com.arkivanov.decompose.value.Value
import ru.take.it.word.learn.game.ui.kit.button.ButtonItem
import ru.take.it.word.learn.game.ui.kit.button_icon.ButtonIconItem
import ru.take.it.word.learn.game.ui.kit.field.TextFieldItem

interface WordCreatorComponent {
    val wordValue: Value<TextFieldItem.State>
    val translateValue: Value<List<TextFieldItem.State>>
    val saveValue: Value<ButtonItem.State>
    val addTranslateValue: Value<ButtonIconItem.State>

    fun onClickRemove(id: String)
}