package ru.take.it.word.learn.game.ui.kit.request

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import ru.take.it.word.learn.game.ui.theme.Bold_20

@Composable
fun EmptyContent(
    modifier: Modifier,
    state: RequestItem.State.Empty
) {
    Text(
        modifier = modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        text = state.message,
        style = Bold_20,
        color = Color.Black
    )
}