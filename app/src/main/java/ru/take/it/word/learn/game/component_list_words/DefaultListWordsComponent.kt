package ru.take.it.word.learn.game.component_list_words

import androidx.compose.ui.text.AnnotatedString
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.retainedInstance
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.take.it.word.learn.game.R
import ru.take.it.word.learn.game.component_creator_word.WordCreatorComponent
import ru.take.it.word.learn.game.core.navigation.dialog.DialogNavigation
import ru.take.it.word.learn.game.core.navigation.screen.ScreenNavigation
import ru.take.it.word.learn.game.tools.ResManager
import ru.take.it.word.learn.game.ui.kit.request.RequestItem
import ru.take.it.word.learn.game.ui.kit.toolbar.ToolbarItem
import ru.take.it.word.learn.game.ui.kit.word.WordItem

class DefaultListWordsComponent(
    componentContext: ComponentContext,
) : ListWordsComponent,
    ComponentContext by componentContext, KoinComponent,
    WordCreatorComponent.Provider {

    private val screenNavigation by inject<ScreenNavigation>()
    private val dialogNavigation by inject<DialogNavigation>()
    private val resManager by inject<ResManager>()

    private val handler = retainedInstance {
        ListWordsHandler(
            scope = coroutineScope(Dispatchers.Main + SupervisorJob())
        )
    }

    override val toolbarState: Value<ToolbarItem.State> = MutableValue(
        ToolbarItem.State(
            title = AnnotatedString(text = resManager.getString(R.string.list_word_title)),
            onClickTrailing = ::onClickNewWord
        )
    )
    override val wordsListState: Value<List<WordItem.State>> by handler::wordsListState
    override val requestState: Value<RequestItem.State> by handler::requestState

    private fun onClickNewWord() {
        dialogNavigation.gotoNewWord(this)
    }

    override fun onAddNewWord() {
        handler.refresh()
    }

    override fun onUpdateWord() {

    }
}