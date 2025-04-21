package com.raya_challenge

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.designsystem.theme.CoinTheme
import com.designsystem.theme.Colors

@Composable
fun App() {
    CoinTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Colors().background
        ) {
            Text("aa")
        }
    }
}