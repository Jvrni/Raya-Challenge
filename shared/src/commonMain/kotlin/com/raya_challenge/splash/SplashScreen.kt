package com.raya_challenge.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.raya_challenge.splash.contract.SplashContract
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import raya_challenge.shared.generated.resources.Res
import raya_challenge.shared.generated.resources.coin_black
import raya_challenge.shared.generated.resources.coin_light

/**
 * Displays the splash screen with an animated logo.
 *
 * This composable shows a full-screen splash animation featuring a coin logo. The animation
 * consists of a fade-in and slide-in effect followed by a fade-out and slide-out effect.
 * After the animation, it navigates to the home screen.
 *
 * @param state The [SplashContract.State] that contains the animation's configuration,
 *              such as the duration, delay, and offset values.
 * @param event A lambda function that handles events triggered by the splash screen,
 *              such as navigating to the home screen. It receives a [SplashContract.Event]
 *              as a parameter.
 * @param viewModel The [SplashViewModel] instance associated with this screen. It's not used
 *                 directly within this composable but is passed through for potential use in
 *                 callbacks or other parts of the application.
 */
@Composable
fun SplashScreen(
    state: SplashContract.State,
    event: (SplashContract.Event) -> Unit,
    viewModel: SplashViewModel
) {
    val animationVisibility = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedVisibility(
            visible = animationVisibility.value,
            enter = fadeIn(animationSpec = tween(state.animationDuration)) + slideInHorizontally(
                animationSpec = tween(
                    state.animationDuration
                ), initialOffsetX = { state.initialOffsetX }),
            exit = fadeOut(animationSpec = tween(state.animationDuration)) + slideOutHorizontally(
                animationSpec = tween(
                    state.animationDuration
                ), targetOffsetX = { state.targetOffsetX })
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
        delay(state.delay)
        animationVisibility.value = false
        delay(state.delay / 2)

        event.invoke(SplashContract.Event.NavigateToHome)
    }
}