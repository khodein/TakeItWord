package ru.take.it.word.learn.game.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.take.it.word.learn.game.component_list_words.ListWordsComponent
import ru.take.it.word.learn.game.ui.kit.toolbar.ToolbarContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp
import ru.take.it.word.learn.game.ui.kit.word.WordContent

@Composable
fun WordsContent(
    component: ListWordsComponent,
    modifier: Modifier,
) {
    Scaffold(
        modifier = modifier.background(MaterialTheme.colorScheme.background),
        topBar = {
            val toolbarFlow by component.toolbarState.subscribeAsState()

            ToolbarContent(
                modifier = Modifier.fillMaxWidth(),
                state = toolbarFlow
            )
        },
        content = { paddingValues ->

            val listFlow by component.wordsListState.subscribeAsState()

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
                    .padding(top = 12.dp),
            ) {
                items(listFlow) { state ->
                    WordContent(
                        state = state,
                        modifier = Modifier
                    )
                }
            }
        }
    )
}