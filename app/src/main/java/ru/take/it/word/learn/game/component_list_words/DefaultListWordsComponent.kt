package ru.take.it.word.learn.game.component_list_words

import androidx.compose.ui.text.AnnotatedString
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.take.it.word.learn.game.core.navigation.dialog.DialogNavigation
import ru.take.it.word.learn.game.core.navigation.screen.ScreenNavigation
import ru.take.it.word.learn.game.ui.kit.toolbar.ToolbarItem
import ru.take.it.word.learn.game.ui.kit.word.WordItem

class DefaultListWordsComponent(
    componentContext: ComponentContext,
) : ListWordsComponent, ComponentContext by componentContext, KoinComponent {

    private val screenNavigation by inject<ScreenNavigation>()
    private val dialogNavigation by inject<DialogNavigation>()

    override val toolbarState: Value<ToolbarItem.State> = MutableValue(
        ToolbarItem.State(
            title = AnnotatedString(text = "Твои слова"),
            onClickTrailing = ::onAddNewWord
        )
    )
    override val wordsListState: Value<List<WordItem.State>> = MutableValue(
        listOf(
            WordItem.State(
                id = "",
                wordText = "kskklsdkl",
                wordTranslate = "sdsdsdsd"
            )
        )
    )

    private fun onAddNewWord() {
        dialogNavigation.gotoNewWord()
    }
}