package ru.take.it.word.learn.game.ui.kit.toolbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import ru.take.it.word.learn.game.R
import ru.take.it.word.learn.game.ui.theme.Bold_20

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolbarContent(
    modifier: Modifier,
    state: ToolbarItem.State,
) {
    TopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = MaterialTheme.colorScheme.inversePrimary
        ),
        title = {
            Text(
                maxLines = 1,
                text = state.title,
                color = Color.Black,
                style = Bold_20,
            )
        },
        navigationIcon = {
            state.onClickLeading?.let {
                IconContent(
                    modifier = Modifier,
                    painter = painterResource(R.drawable.ic_arrow_back),
                    onClick = it
                )
            }
        },
        actions = {
            state.onClickTrailing?.let {
                IconContent(
                    modifier = Modifier,
                    painter = painterResource(R.drawable.ic_plus),
                    onClick = it
                )
            }
        }
    )
}

@Composable
private fun IconContent(
    modifier: Modifier,
    painter: Painter,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .clickable {
                onClick.invoke()
            }
            .padding(horizontal = 20.dp)
    ) {
        Image(
            painter = painter,
            colorFilter = ColorFilter.tint(Color.Black),
            modifier = Modifier
                .size(16.dp)
                .align(Alignment.Center),
            contentDescription = "ToolbarContent.Icon",
        )
    }
}
