package com.designsystem.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Light Theme Colors
val DarkGrayPrimary = Color(0xFF4A4A4A)
val LightGrayButton = Color(0xFFB0B0B0)
val LightBackground = Color(0xFFF8F8F8)
val LightTextColor = Color(0xFF333333)

// Dark Theme Colors
val WhitePrimary = Color(0xFFFFFFFF)
val DarkGrayButton = Color(0xFF4A4A4A)
val DarkBackground = Color(0xFF1C1C1C)
val DarkSurface = Color(0xFF2A2A2A)
val DarkTextColor = Color(0xFFFFFFFF)

internal val LightColorScheme = lightColorScheme(
    primary = DarkGrayPrimary,
    background = WhitePrimary,
    onBackground = DarkGrayPrimary,
    secondary = LightGrayButton,
    onSecondary = LightTextColor,
    tertiary = LightBackground,
    onTertiary = WhitePrimary
)

internal val DarkColorScheme = darkColorScheme(
    primary = WhitePrimary,
    background = DarkBackground,
    onBackground = DarkTextColor,
    surface = DarkSurface,
    onSurface = WhitePrimary,
    secondary = DarkGrayButton,
    onSecondary = DarkTextColor,
    tertiary = DarkGrayPrimary,
    onTertiary = DarkBackground
)