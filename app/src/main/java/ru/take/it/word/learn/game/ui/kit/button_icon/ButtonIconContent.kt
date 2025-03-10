package ru.take.it.word.learn.game.ui.kit.button_icon

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ButtonIconContent(
    modifier: Modifier,
    state: ButtonIconItem.State
) {
    IconButton(
        modifier = modifier
            .size(32.dp),
        colors = IconButtonDefaults.iconButtonColors().copy(
            containerColor = state.containerColor ?: MaterialTheme.colorScheme.primary
        ),
        onClick = {
            state.onClick?.invoke()
        },
        content = {
            Icon(
                painter = painterResource(state.icon),
                tint = Color.White,
                contentDescription = "ButtonIconContent.Icon",
                modifier = Modifier.size(16.dp),
            )
        }
    )
}