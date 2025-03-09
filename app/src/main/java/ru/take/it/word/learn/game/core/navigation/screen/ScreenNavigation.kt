package ru.take.it.word.learn.game.core.navigation.screen

import com.arkivanov.decompose.router.stack.StackNavigation
import ru.take.it.word.learn.game.core.navigation.ScreenNavigationConfig

interface ScreenNavigation {
    fun getStackNavigation(): StackNavigation<ScreenNavigationConfig>
    fun pop()
}