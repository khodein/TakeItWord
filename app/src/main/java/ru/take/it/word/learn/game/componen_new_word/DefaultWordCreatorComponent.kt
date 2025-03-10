package ru.take.it.word.learn.game.componen_new_word

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.retainedInstance
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import ru.take.it.word.learn.game.ui.kit.button.ButtonItem
import ru.take.it.word.learn.game.ui.kit.button_icon.ButtonIconItem
import ru.take.it.word.learn.game.ui.kit.field.TextFieldItem

class DefaultWordCreatorComponent(
    componentContext: ComponentContext,
) : WordCreatorComponent, ComponentContext by componentContext {

    private val handler = retainedInstance {
        WordCreatorHandler(
            scope = coroutineScope(Dispatchers.Main + SupervisorJob()),
        )
    }

    override val wordValue: Value<TextFieldItem.State> by handler::wordValue
    override val translateValue: Value<List<TextFieldItem.State>> by handler::translateValue
    override val saveValue: Value<ButtonItem.Default> by handler::saveValue
    override val addTranslateValue: Value<ButtonIconItem.State> by handler::addTranslateValue

    override fun onClickRemove(id: String) {
        handler.onClickRemoveTranslate(id)
    }
}