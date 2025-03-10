package ru.take.it.word.learn.game.ui.kit.button

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.take.it.word.learn.game.ui.kit.request.RequestItem
import ru.take.it.word.learn.game.ui.theme.Bold_20

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

@Composable
fun ButtonTextContent(
    modifier: Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
        style = Bold_20,
        color = MaterialTheme.colorScheme.onPrimary
    )
}