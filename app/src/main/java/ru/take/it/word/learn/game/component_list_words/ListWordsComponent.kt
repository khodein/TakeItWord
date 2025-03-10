package ru.take.it.word.learn.game.component_list_words

import com.arkivanov.decompose.value.Value
import ru.take.it.word.learn.game.ui.kit.request.RequestItem
import ru.take.it.word.learn.game.ui.kit.toolbar.ToolbarItem
import ru.take.it.word.learn.game.ui.kit.word.WordItem

interface ListWordsComponent {

    val toolbarState: Value<ToolbarItem.State>

    val wordsListState: Value<List<WordItem.State>>

    val requestState: Value<RequestItem.State>
}