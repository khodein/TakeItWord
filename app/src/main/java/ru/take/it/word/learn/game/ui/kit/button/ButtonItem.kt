package ru.take.it.word.learn.game.ui.kit.button

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.take.it.word.learn.game.ui.kit.request.RequestItem

class ButtonItem {

    interface State {
        val id: String
        val value: String
        val cornerSize: CornerSize
        val requestState: RequestItem.State
        val isEnabled: Boolean
        val onClick: (() -> Unit)?
    }

    data class Default(
        override val id: String,
        override val value: String,
        override val cornerSize: CornerSize = CornerSize(16.dp),
        override val requestState: RequestItem.State = RequestItem.State.Idle,
        override val isEnabled: Boolean = true,
        override val onClick: (() -> Unit)? = null,
    ) : State

    data class Outline(
        override val id: String,
        val borderStroke: Dp = 1.dp,
        override val value: String,
        override val cornerSize: CornerSize = CornerSize(16.dp),
        override val requestState: RequestItem.State = RequestItem.State.Idle,
        override val isEnabled: Boolean = true,
        override val onClick: (() -> Unit)? = null,
    ) : State
}