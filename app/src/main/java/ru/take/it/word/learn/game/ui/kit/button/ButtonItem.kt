package ru.take.it.word.learn.game.ui.kit.button

import ru.take.it.word.learn.game.ui.kit.request.RequestItem

class ButtonItem {

    data class State(
        val id: String,
        val value: String,
        val requestState: RequestItem.State = RequestItem.State.Idle,
        val isEnabled: Boolean = true,
        val onClick: (() -> Unit)? = null
    )
}