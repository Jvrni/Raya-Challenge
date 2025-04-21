package com.raya_challenge.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import raya_challenge.shared.generated.resources.Res
import raya_challenge.shared.generated.resources.coin_black
import raya_challenge.shared.generated.resources.coin_light

@Composable
fun SplashScreen() {
    val animationVisibility = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedVisibility(
            visible = animationVisibility.value,
            enter = fadeIn(animationSpec = tween(800)) + slideInHorizontally(
                animationSpec = tween(
                    800
                ), initialOffsetX = { 700 }),
            exit = fadeOut(animationSpec = tween(800)) + slideOutHorizontally(
                animationSpec = tween(
                    800
                ), targetOffsetX = { -700 })
        ) {
            Image(
                painter = painterResource(
                    if (isSystemInDarkTheme()) Res.drawable.coin_light
                    else Res.drawable.coin_black
                ),
                contentDescription = ""
            )
        }
    }

    LaunchedEffect(Unit) {
        animationVisibility.value = true
        delay(2000L)
        animationVisibility.value = false
    }
}