package ru.take.it.word.learn.game.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.take.it.word.learn.game.R
import ru.take.it.word.learn.game.component_creator_word.WordCreatorComponent
import ru.take.it.word.learn.game.ui.kit.button.DefaultButtonContent
import ru.take.it.word.learn.game.ui.kit.button_icon.ButtonIconContent
import ru.take.it.word.learn.game.ui.kit.button_icon.ButtonIconItem
import ru.take.it.word.learn.game.ui.kit.field.TextFieldContent
import ru.take.it.word.learn.game.ui.kit.field.TextFieldItem
import ru.take.it.word.learn.game.ui.theme.Bold_20
import ru.take.it.word.learn.game.ui.theme.Regular_10

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WordCreatorContent(
    modifier: Modifier,
    onDismiss: () -> Unit,
    component: WordCreatorComponent,
) {

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )

    ModalBottomSheet(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .statusBarsPadding()
            .navigationBarsPadding()
            .displayCutoutPadding(),
        dragHandle = null,
        sheetState = sheetState,
        properties = ModalBottomSheetDefaults.properties,
        onDismissRequest = onDismiss,
    ) {
        val translateListFlow by component.translateValue.subscribeAsState()

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(start = 16.dp, top = 20.dp, bottom = 12.dp),
                text = stringResource(R.string.create_word_title),
                textAlign = TextAlign.Start,
                style = Bold_20,
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                item {
                    val wordFlow by component.wordValue.subscribeAsState()

                    TextFieldContent(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth(),
                        state = wordFlow
                    )
                }

                val size = translateListFlow.size
                itemsIndexed(translateListFlow) { index: Int, state: TextFieldItem.State ->
                    val paddingTop = if (index == 0) {
                        16.dp
                    } else {
                        0.dp
                    }
                    val paddingBottom = if (index == translateListFlow.lastIndex) {
                        16.dp
                    } else {
                        0.dp
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(top = paddingTop, bottom = paddingBottom)
                    ) {
                        TextFieldContent(
                            modifier = Modifier
                                .weight(1f)
                                .align(Alignment.CenterVertically)
                                .padding(end = 8.dp),
                            state = state,
                        )

                        when {
                            index == 0 && size < 5 -> {
                                val buttonIconState = ButtonIconItem.State(
                                    id = "add_translate_id",
                                    icon = R.drawable.ic_plus,
                                    onClick = component::onClickAdd
                                )

                                ButtonIconContent(
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .padding(bottom = 12.dp),
                                    state = buttonIconState
                                )
                            }

                            index in 1..4 -> {
                                val buttonIconState = ButtonIconItem.State(
                                    id = "remove_translate_id",
                                    icon = R.drawable.ic_remove,
                                    containerColor = Color.Red,
                                    onClick = {
                                        component.onClickRemove(state.id)
                                    }
                                )

                                ButtonIconContent(
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .padding(bottom = 12.dp),
                                    state = buttonIconState
                                )
                            }
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {

                val message by component.messageTopButtonValue.subscribeAsState()

                Text(
                    style = Regular_10,
                    color = if (message.isError) Color.Red else Color.Gray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp)
                        .padding(horizontal = 20.dp),
                    text = message.message,
                    textAlign = TextAlign.Center
                )

                val saveFlow by component.saveValue.subscribeAsState()

                DefaultButtonContent(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 16.dp)
                        .fillMaxWidth(),
                    state = saveFlow
                )
            }
        }
    }
}