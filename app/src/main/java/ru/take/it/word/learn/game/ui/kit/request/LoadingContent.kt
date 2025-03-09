package ru.take.it.word.learn.game.ui.kit.request

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadingContent(
    modifier: Modifier = Modifier.size(40.dp),
    state: RequestItem.State.Loading
) {
    CircularProgressIndicator(
        modifier = modifier,
        trackColor = state.trackColor,
        strokeWidth = state.strokeWidth
    )
}