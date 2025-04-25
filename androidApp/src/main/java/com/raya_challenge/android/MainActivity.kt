package com.raya_challenge.android

import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import com.raya_challenge.App

/**
 * The main activity of the application.
 *
 * This class is the entry point for the application and is responsible for
 * setting up the UI, enabling edge-to-edge display, and configuring the
 * status bar color.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            SetStatusBarColor()
            App()
        }
    }

    @Composable
    private fun SetStatusBarColor() {
        val statusBarColor = MaterialTheme.colorScheme.background

        SideEffect {
            enableEdgeToEdge(SystemBarStyle.auto(statusBarColor.toArgb(), statusBarColor.toArgb()))
        }
    }
}
