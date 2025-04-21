package com.raya_challenge.splash

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.navigation.Destinations

fun NavGraphBuilder.splashGraph() {
    composable<Destinations.Splash> {
        SplashScreen()
    }
}