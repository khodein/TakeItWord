package ru.take.it.word.learn.game.ui.kit.word

class WordItem {

    data class State(
        val id: String,
        val wordText: String,
        val wordTranslate: String,
        val onClickItem: (() -> Unit)? = null
    )
}