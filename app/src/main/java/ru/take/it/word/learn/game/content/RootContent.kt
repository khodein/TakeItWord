package ru.take.it.word.learn.game.content

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ExperimentalDecomposeApi
import ru.take.it.word.learn.game.component_root.RootComponent
import ru.take.it.word.learn.game.ui.theme.TakeItWordTheme
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.predictiveBackAnimation
import com.arkivanov.decompose.extensions.compose.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState

@Composable
fun RootContent(
    component: RootComponent,
    modifier: Modifier = Modifier,
) {
    TakeItWordTheme {
        Children(
            component = component,
            modifier = modifier,
        )
    }
}

@OptIn(ExperimentalDecomposeApi::class)
@Composable
private fun Children(component: RootComponent, modifier: Modifier = Modifier) {
    Children(
        stack = component.stack,
        modifier = modifier,
        animation = predictiveBackAnimation(
            backHandler = component.backHandler,
            fallbackAnimation = stackAnimation(fade() + scale()),
            onBack = component::pop,
        ),
    ) { children ->
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            when (val child = children.instance) {
                is RootComponent.ChildComponent.ListWordsChild -> WordsContent(
                    modifier = modifier,
                    component = child.component
                )
            }
        }
    }

    val slotFlow by component.slot.subscribeAsState()

    when (val slotInstance = slotFlow.child?.instance) {
        is RootComponent.SlotChildComponent.WordCreatorChild -> {
            WordCreatorContent(
                modifier = Modifier,
                onDismiss = component::dialogDismiss,
                component = slotInstance.component
            )
        }

        else -> Unit
    }
}
