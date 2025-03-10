package ru.take.it.word.learn.game.ui.kit.request

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.take.it.word.learn.game.ui.theme.Bold_20

@Composable
fun ErrorContent(
    modifier: Modifier,
    state: RequestItem.State.Error
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .clickable {
                state.onClickReload?.invoke()
            },
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = state.message.orEmpty(),
            style = Bold_20,
            color = MaterialTheme.colorScheme.inversePrimary,
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            textAlign = TextAlign.Center,
            text = "Нажмите чтобы обновить!",
            style = Bold_20,
            color = Color.Black
        )
    }
}