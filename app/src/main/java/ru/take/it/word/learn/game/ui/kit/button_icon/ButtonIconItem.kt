package ru.take.it.word.learn.game.ui.kit.button_icon

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

class ButtonIconItem {

    data class State(
        val id: String,
        @DrawableRes val icon: Int,
        val containerColor: Color? = null,
        val onClick: (() -> Unit)? = null
    )
}