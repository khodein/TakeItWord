package ru.take.it.word.learn.game.ui.kit.field

class TextFieldItem {

    data class State(
        val id: String,
        val text: String,
        val label: String,
        val isSingleLine: Boolean = true,
        val onChangeValue: (value: String) -> Unit
    )
}