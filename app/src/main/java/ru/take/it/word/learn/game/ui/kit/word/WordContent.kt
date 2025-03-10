package ru.take.it.word.learn.game.ui.kit.word

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.take.it.word.learn.game.ui.theme.Regular_10
import ru.take.it.word.learn.game.ui.theme.Regular_16

@Composable
fun WordContent(
    modifier: Modifier,
    state: WordItem.State,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .border(
                border = BorderStroke(
                    0.2.dp,
                    MaterialTheme.colorScheme.inversePrimary
                ),
                shape = RoundedCornerShape(CornerSize(12.dp))
            )
            .clip(RoundedCornerShape(CornerSize(12.dp)))
            .clickable {
                state.onClickItem?.invoke()
            }
            .background(Color.White)
            .padding(vertical = 6.dp)

    ) {
        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 16.dp),
        ) {
            Text(
                modifier = Modifier.padding(bottom = 4.dp),
                text = state.wordTranslate,
                overflow = TextOverflow.Ellipsis,
                style = Regular_10,
                maxLines = 1,
                color = Color.Gray,
            )
            Text(
                modifier = Modifier,
                text = state.wordText,
                style = Regular_16,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                color = Color.Black
            )
        }

    }
}