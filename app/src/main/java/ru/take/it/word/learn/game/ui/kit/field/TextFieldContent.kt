package ru.take.it.word.learn.game.ui.kit.field

import android.view.ViewTreeObserver
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.take.it.word.learn.game.ui.theme.Regular_10
import ru.take.it.word.learn.game.ui.theme.Regular_16

@Composable
fun TextFieldContent(
    modifier: Modifier,
    state: TextFieldItem.State
) {
    val focusRequester = remember { FocusRequester() }
    var labelStyle by remember { mutableStateOf(Regular_16) }

    val view = LocalView.current
    val viewTreeObserver = view.viewTreeObserver
    DisposableEffect(viewTreeObserver) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val isKeyboardOpen = ViewCompat.getRootWindowInsets(view)
                ?.isVisible(WindowInsetsCompat.Type.ime()) ?: true
            if (!isKeyboardOpen) {
                focusRequester.freeFocus()
            }
        }

        viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose {
            viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }

    OutlinedTextField(
        modifier = modifier
            .focusRequester(focusRequester)
            .onFocusChanged {
                labelStyle = if (it.hasFocus) {
                    Regular_10
                } else {
                    Regular_16
                }

            },
        shape = RoundedCornerShape(CornerSize(16.dp)),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            showKeyboardOnFocus = true
        ),
        singleLine = state.isSingleLine,
        value = state.text,
        label = {
            Text(
                style = labelStyle,
                text = state.label
            )
        },
        onValueChange = { value ->
            state.onChangeValue.invoke(value)
        }
    )
}