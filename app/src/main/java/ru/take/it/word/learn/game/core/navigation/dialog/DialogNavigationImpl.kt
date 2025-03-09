package ru.take.it.word.learn.game.core.navigation.dialog

import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.router.slot.navigate
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.take.it.word.learn.game.core.navigation.DialogNavigationConfig

private class DialogNavigationImpl : DialogNavigation {

    private val slotNavigation = SlotNavigation<DialogNavigationConfig>()

    override fun getSlotNavigation(): SlotNavigation<DialogNavigationConfig> {
        return slotNavigation
    }

    override fun gotoNewWord() {
        slotNavigation.activate(DialogNavigationConfig.NewWord)
    }

    override fun dismiss() {
        slotNavigation.dismiss()
    }
}

val dialogNavigationModule = module {
    singleOf<DialogNavigation>(::DialogNavigationImpl)
}