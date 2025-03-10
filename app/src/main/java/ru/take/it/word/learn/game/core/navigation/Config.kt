package ru.take.it.word.learn.game.core.navigation

import kotlinx.serialization.Serializable
import ru.take.it.word.learn.game.component_creator_word.WordCreatorComponent

@Serializable
sealed class ScreenNavigationConfig {

    @Serializable
    data object Words : ScreenNavigationConfig()
}

@Serializable
sealed class DialogNavigationConfig {

    @Serializable
    data class NewWord(
        val provider: WordCreatorComponent.Provider
    ) : DialogNavigationConfig()
}