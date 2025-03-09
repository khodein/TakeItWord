package ru.take.it.word.learn.game.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.take.it.word.learn.game.componen_new_word.WordCreatorComponent
import ru.take.it.word.learn.game.ui.kit.button.ButtonContent
import ru.take.it.word.learn.game.ui.kit.field.TextFieldContent
import ru.take.it.word.learn.game.ui.theme.Bold_20
import ru.take.it.word.learn.game.ui.theme.Regular_10

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WordCreatorContent(
    modifier: Modifier,
    onDismiss: () -> Unit,
    component: WordCreatorComponent,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .statusBarsPadding()
            .navigationBarsPadding()
            .displayCutoutPadding(),
        dragHandle = null,
        sheetState = sheetState,
        properties = ModalBottomSheetDefaults.properties,
        onDismissRequest = onDismiss,
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(start = 16.dp, top = 20.dp, bottom = 12.dp),
                    text = "Новое слово \uD83D\uDE0A",
                    textAlign = TextAlign.Start,
                    style = Bold_20,
                )
            }

            item {
                val wordFlow by component.wordValue.subscribeAsState()

                TextFieldContent(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    state = wordFlow
                )
            }

            item {
                val translateFlow by component.translateValue.subscribeAsState()

                TextFieldContent(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp)
                        .fillMaxWidth(),
                    state = translateFlow
                )
            }

            item {
                Spacer(modifier.height(24.dp))
            }

            item {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        style = Regular_10,
                        color = Color.Gray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp)
                            .padding(horizontal = 20.dp),
                        text = "Введите слово на английском и перевод,\nне переживайте вы всегда сможете его отредактировать",
                        textAlign = TextAlign.Center
                    )

                    val saveFlow by component.saveValue.subscribeAsState()

                    ButtonContent(
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
}