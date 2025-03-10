package ru.take.it.word.learn.game.ui.kit.field

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.ui.text.input.ImeAction

class TextFieldItem {

    data class State(
        val id: String,
        val text: String,
        val label: String,
        val isFocus: Boolean = false,
        val imeAction: ImeAction = ImeAction.Done,
        val keyboardActions: KeyboardActions = KeyboardActions(),
        val supportText: String? = null,
        val isSingleLine: Boolean = true,
        val onChangeValue: (id: String, value: String) -> Unit
    )
}