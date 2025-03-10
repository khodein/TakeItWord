package ru.take.it.word.learn.game.component_root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.take.it.word.learn.game.component_creator_word.DefaultWordCreatorComponent
import ru.take.it.word.learn.game.component_list_words.DefaultListWordsComponent
import ru.take.it.word.learn.game.core.navigation.DialogNavigationConfig
import ru.take.it.word.learn.game.core.navigation.ScreenNavigationConfig
import ru.take.it.word.learn.game.core.navigation.dialog.DialogNavigation
import ru.take.it.word.learn.game.core.navigation.screen.ScreenNavigation

class DefaultRootComponent(
    componentContext: ComponentContext
) : RootComponent, KoinComponent, ComponentContext by componentContext {

    private val screenNavigation by inject<ScreenNavigation>()
    private val dialogNavigation by inject<DialogNavigation>()

    private val _stack =
        childStack(
            source = screenNavigation.getStackNavigation(),
            serializer = ScreenNavigationConfig.serializer(),
            initialStack = ::getInitialStack,
            childFactory = ::getChild,
        )

    private val _slot =
        childSlot(
            source = dialogNavigation.getSlotNavigation(),
            serializer = DialogNavigationConfig.serializer(),
            handleBackButton = true,
            childFactory = ::getSlot
        )


    override val stack: Value<ChildStack<*, RootComponent.ChildComponent>> = _stack
    override val slot: Value<ChildSlot<*, RootComponent.SlotChildComponent>> = _slot

    override fun pop() {
        screenNavigation.pop()
    }

    override fun dialogDismiss() {
        dialogNavigation.dismiss()
    }

    private fun getChild(
        config: ScreenNavigationConfig,
        componentContext: ComponentContext,
    ): RootComponent.ChildComponent {
        return when (config) {
            is ScreenNavigationConfig.Words -> {
                RootComponent.ChildComponent.ListWordsChild(
                    DefaultListWordsComponent(
                        componentContext
                    )
                )
            }
        }
    }

    private fun getSlot(
        config: DialogNavigationConfig,
        componentContext: ComponentContext
    ): RootComponent.SlotChildComponent {
        return when (config) {
            is DialogNavigationConfig.NewWord -> {
                RootComponent.SlotChildComponent.WordCreatorChild(
                    DefaultWordCreatorComponent(
                        provider = config.provider,
                        componentContext = componentContext,
                    )
                )
            }
        }
    }

    private fun getInitialStack(): List<ScreenNavigationConfig> {
        return listOf(ScreenNavigationConfig.Words)
    }
}