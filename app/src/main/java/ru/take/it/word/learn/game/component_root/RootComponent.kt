package ru.take.it.word.learn.game.component_root

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import ru.take.it.word.learn.game.componen_new_word.WordCreatorComponent
import ru.take.it.word.learn.game.component_list_words.ListWordsComponent

interface RootComponent : BackHandlerOwner {

    val stack: Value<ChildStack<*, ChildComponent>>
    val slot: Value<ChildSlot<*, SlotChildComponent>>

    fun pop()
    fun dialogDismiss()

    sealed class ChildComponent {
        data class ListWordsChild(val component: ListWordsComponent) : ChildComponent()
    }

    sealed class SlotChildComponent {
        data class WordCreatorChild(val component: WordCreatorComponent): SlotChildComponent()
    }
}