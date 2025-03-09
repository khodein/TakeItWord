package ru.take.it.word.learn.game.ui.kit.toolbar

import androidx.compose.ui.text.AnnotatedString

class ToolbarItem {

    data class State(
        val title: AnnotatedString,
        val onClickTrailing: (() -> Unit)? = null,
        val onClickLeading: (() -> Unit)? = null
    )
}