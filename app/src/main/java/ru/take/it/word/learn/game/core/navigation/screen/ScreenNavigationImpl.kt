package ru.take.it.word.learn.game.core.navigation.screen

import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.router.stack.replaceCurrent
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.take.it.word.learn.game.core.navigation.ScreenNavigationConfig

private class ScreenNavigationImpl : ScreenNavigation {

    private val stackNavigation = StackNavigation<ScreenNavigationConfig>()

    private fun pushNew(config: ScreenNavigationConfig) {
        stackNavigation.pushNew(config)
    }

    private fun replaceCurrent(config: ScreenNavigationConfig) {
        stackNavigation.replaceCurrent(config)
    }

    override fun getStackNavigation(): StackNavigation<ScreenNavigationConfig> {
        return stackNavigation
    }

    override fun pop() {
        stackNavigation.pop()
    }

    private fun pop(toIndex: Int) {
        stackNavigation.popTo(index = toIndex)
    }
}

val screenNavigationModule = module {
    singleOf<ScreenNavigation>(::ScreenNavigationImpl)
}

