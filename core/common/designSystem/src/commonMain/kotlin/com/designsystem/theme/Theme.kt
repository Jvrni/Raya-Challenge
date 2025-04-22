package com.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun CoinTheme(content: @Composable () -> Unit) {
    MaterialTheme(content = content, colorScheme = Colors())
}
