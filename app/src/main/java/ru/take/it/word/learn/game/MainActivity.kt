package ru.take.it.word.learn.game

import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.core.view.WindowInsetsControllerCompat
import com.arkivanov.decompose.defaultComponentContext
import ru.take.it.word.learn.game.component_root.DefaultRootComponent
import ru.take.it.word.learn.game.component_root.RootComponent
import ru.take.it.word.learn.game.content.RootContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

        enableEdgeToEdge(statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT))

        val rootComponent: RootComponent = DefaultRootComponent(defaultComponentContext())

        setContent {
            SideEffect {
                setNavAndStatusBar()
            }
            RootContent(
                component = rootComponent,
                modifier = Modifier.fillMaxSize()
            )
        }
    }

    private fun setNavAndStatusBar() {
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> {
                window.navigationBarColor = Color.BLACK
                window.statusBarColor = Color.TRANSPARENT
                WindowInsetsControllerCompat(window, window.decorView).apply {
                    isAppearanceLightStatusBars = true
                    isAppearanceLightNavigationBars = false
                }
            }

            Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1 -> {
                var flags = window.decorView.systemUiVisibility
                window.navigationBarColor = Color.BLACK
                window.statusBarColor = Color.TRANSPARENT
                flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                window.decorView.systemUiVisibility = flags
            }

            else -> {
                window.navigationBarColor = Color.BLACK
                window.statusBarColor = Color.TRANSPARENT
            }
        }
    }
}