package ru.take.it.word.learn.game.core.navigation.dialog

import com.arkivanov.decompose.router.slot.SlotNavigation
import ru.take.it.word.learn.game.component_creator_word.WordCreatorComponent
import ru.take.it.word.learn.game.core.navigation.DialogNavigationConfig

interface DialogNavigation {
    fun getSlotNavigation(): SlotNavigation<DialogNavigationConfig>
    fun gotoNewWord(creatorProvider: WordCreatorComponent.Provider)

    fun dismiss()
}