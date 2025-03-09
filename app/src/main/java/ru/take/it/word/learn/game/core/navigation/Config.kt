package ru.take.it.word.learn.game.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class ScreenNavigationConfig {

    @Serializable
    data object Words : ScreenNavigationConfig()
}

@Serializable
sealed class DialogNavigationConfig {

    @Serializable
    data object NewWord: DialogNavigationConfig()
}