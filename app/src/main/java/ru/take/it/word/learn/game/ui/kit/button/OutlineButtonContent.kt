package ru.take.it.word.learn.game.ui.kit.button

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.take.it.word.learn.game.ui.kit.request.LoadingContent
import ru.take.it.word.learn.game.ui.kit.request.RequestItem

@Composable
fun OutlineButtonContent(
    modifier: Modifier,
    state: ButtonItem.Outline,
) {
    OutlinedButton(
        modifier = modifier.height(60.dp),
        shape = RoundedCornerShape(CornerSize(16.dp)),
        enabled = state.isEnabled,
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        border = ButtonDefaults.outlinedButtonBorder(state.isEnabled).copy(
            width = state.borderStroke
        ),
        onClick = {
            if (state.requestState is RequestItem.State.Idle) {
                state.onClick?.invoke()
            }
        },
        content = {
            when (val requestState = state.requestState) {
                is RequestItem.State.Idle,
                is RequestItem.State.Error,
                is RequestItem.State.Success -> {
                    ButtonTextContent(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        text = state.value
                    )
                }

                is RequestItem.State.Loading -> {
                    LoadingContent(state = requestState)
                }

                else -> Unit
            }
        }
    )
}