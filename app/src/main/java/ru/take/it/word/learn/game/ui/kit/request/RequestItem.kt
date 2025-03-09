package ru.take.it.word.learn.game.ui.kit.request

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class RequestItem {

    sealed class State(
        val id: String
    ) {
        data class Loading(
            val trackColor: Color = Color.Black,
            val strokeWidth: Dp = 4.dp
        ) : State(id = "request_loading_id")

        data object Idle : State(id = "request_idle_id")
    }
}