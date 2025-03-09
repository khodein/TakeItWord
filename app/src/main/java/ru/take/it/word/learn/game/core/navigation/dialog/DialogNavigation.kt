package ru.take.it.word.learn.game.core.navigation.dialog

import com.arkivanov.decompose.router.slot.SlotNavigation
import ru.take.it.word.learn.game.core.navigation.DialogNavigationConfig

interface DialogNavigation {
    fun getSlotNavigation(): SlotNavigation<DialogNavigationConfig>
    fun gotoNewWord()
    fun dismiss()
}