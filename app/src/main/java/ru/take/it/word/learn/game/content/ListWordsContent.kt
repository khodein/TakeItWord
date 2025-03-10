package ru.take.it.word.learn.game.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.take.it.word.learn.game.component_list_words.ListWordsComponent
import ru.take.it.word.learn.game.ui.kit.request.EmptyContent
import ru.take.it.word.learn.game.ui.kit.request.ErrorContent
import ru.take.it.word.learn.game.ui.kit.request.LoadingContent
import ru.take.it.word.learn.game.ui.kit.request.RequestItem
import ru.take.it.word.learn.game.ui.kit.toolbar.ToolbarContent
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

            val requestFlow by component.requestState.subscribeAsState()

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            ) {
                when (val request = requestFlow) {
                    is RequestItem.State.Success -> {
                        val listFlow by component.wordsListState.subscribeAsState()

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.Center),
                            contentPadding = PaddingValues(
                                top = 16.dp,
                                start = 16.dp,
                                end = 16.dp
                            )
                        ) {
                            items(listFlow) { state ->
                                WordContent(
                                    state = state,
                                    modifier = Modifier
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                            }
                        }
                    }

                    is RequestItem.State.Loading -> {
                        LoadingContent(
                            modifier = Modifier
                                .align(Alignment.Center),
                            state = request.copy(trackColor = MaterialTheme.colorScheme.inversePrimary)
                        )
                    }

                    is RequestItem.State.Error -> {
                        ErrorContent(
                            modifier = Modifier
                                .align(Alignment.Center),
                            state = request
                        )
                    }

                    is RequestItem.State.Empty -> {
                        EmptyContent(
                            modifier = Modifier
                                .align(Alignment.Center),
                            state = request
                        )
                    }

                    else -> Unit
                }
            }
        }
    )
}