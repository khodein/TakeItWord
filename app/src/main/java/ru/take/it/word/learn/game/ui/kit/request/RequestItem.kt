package ru.take.it.word.learn.game.ui.kit.request

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class RequestItem {

    sealed class State(
        val id: String
    ) {
        data object Success : State("request_success_id")
        data class Loading(
            val trackColor: Color = Color.Black,
            val strokeWidth: Dp = 4.dp
        ) : State(id = "request_loading_id")

        data object Idle : State(id = "request_idle_id")

        data class Error(
            val message: String? = null,
            val onClickReload: (() -> Unit)? = null
        ) : State(id = "request_error_id")

        data class Empty(
            val message: String,
        ) : State(id = "request_empty_id")
    }
}