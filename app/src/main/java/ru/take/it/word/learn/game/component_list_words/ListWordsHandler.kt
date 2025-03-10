package ru.take.it.word.learn.game.component_list_words

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.take.it.word.learn.game.R
import ru.take.it.word.learn.game.repositories.word.WordRepository
import ru.take.it.word.learn.game.tools.ResManager
import ru.take.it.word.learn.game.ui.kit.request.RequestItem
import ru.take.it.word.learn.game.ui.kit.word.WordItem

class ListWordsHandler(
    private val scope: CoroutineScope
) : InstanceKeeper.Instance, KoinComponent {

    private val wordRepository by inject<WordRepository>()
    private val resManager by inject<ResManager>()

    val wordsListState: MutableValue<List<WordItem.State>> = MutableValue(emptyList())
    val requestState: MutableValue<RequestItem.State> = MutableValue(RequestItem.State.Idle)

    init {
        refresh()
    }

    fun refresh() {
        load()
    }

    private fun load() {
        scope.launch {
            requestState.value = RequestItem.State.Loading()
            runCatching {
                withContext(Dispatchers.IO) {
                    delay(500)
                    wordRepository.getWordsList()
                }
            }.onSuccess { list ->
                requestState.value = if (list.isEmpty()) {
                    RequestItem.State.Empty(
                        message = resManager.getString(R.string.list_word_empty)
                    )
                } else {
                    RequestItem.State.Success
                }
                wordsListState.value = list.map {
                    WordItem.State(
                        id = it.id.toString(),
                        wordText = it.value,
                        wordTranslate = it.translate.replace(",", ", ")
                    )
                }
            }.onFailure {
                requestState.value = RequestItem.State.Error(
                    message = resManager.getString(R.string.list_word_error)
                )
            }
        }
    }
}