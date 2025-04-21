package com.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val primaryLightColor = Color(0xFF6200EE)
val secondaryLightColor = Color(0xFF03DAC5)
val tertiaryLightColor = Color(0xFF3700B3)

val primaryDarkColor = Color(0xFF6200EE)
val secondaryDarkColor = Color(0xFF03DAC5)
val tertiaryDarkColor = Color(0xFF3700B3)


val lightColor = lightColorScheme(
    primary = primaryLightColor,
    secondary = secondaryLightColor,
    tertiary = tertiaryLightColor
)

val darkColor = darkColorScheme(
    primary = primaryDarkColor,
    secondary = secondaryDarkColor,
    tertiary = tertiaryDarkColor
)

@Composable
fun Colors(): ColorScheme = if (isSystemInDarkTheme()) darkColor else lightColor
