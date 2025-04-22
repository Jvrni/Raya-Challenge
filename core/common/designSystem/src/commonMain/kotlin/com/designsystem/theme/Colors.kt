package com.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val primaryLightColor = Color(0xFF6200EE)
val secondaryLightColor = Color(0xFF03DAC5)

val primaryDarkColor = Color(0xFF6200EE)
val secondaryDarkColor = Color(0xFF03DAC5)
val white = Color(0xFFFFFFFF)
val black = Color(0x00000000)


val lightColor = lightColorScheme(
    primary = primaryLightColor,
    secondary = secondaryLightColor,
    tertiary = black,
    background = white
)

val darkColor = darkColorScheme(
    primary = primaryDarkColor,
    secondary = secondaryDarkColor,
    tertiary = white,
    background = black
)

@Composable
fun Colors(): ColorScheme = if (isSystemInDarkTheme()) darkColor else lightColor
