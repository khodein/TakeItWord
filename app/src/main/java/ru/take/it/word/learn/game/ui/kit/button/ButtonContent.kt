package ru.take.it.word.learn.game.ui.kit.button

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.take.it.word.learn.game.ui.kit.request.RequestItem
import ru.take.it.word.learn.game.ui.kit.request.LoadingContent
import ru.take.it.word.learn.game.ui.theme.Bold_20

@Composable
fun ButtonContent(
    modifier: Modifier,
    state: ButtonItem.State
) {
    Button(
        modifier = modifier.height(60.dp),
        shape = RoundedCornerShape(CornerSize(16.dp)),
        enabled = state.isEnabled,
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        onClick = {
            if (state.requestState is RequestItem.State.Idle) {
                state.onClick?.invoke()
            }
        },
        content = {
            when(val requestState = state.requestState) {
                is RequestItem.State.Idle -> {
                    Text(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        text = state.value,
                        style = Bold_20,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
                is RequestItem.State.Loading -> {
                    LoadingContent(
                        state = requestState
                    )
                }
            }
        }
    )
}