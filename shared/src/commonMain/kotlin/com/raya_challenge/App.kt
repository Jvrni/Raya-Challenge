package com.raya_challenge

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.designsystem.theme.CoinTheme
import com.designsystem.theme.Colors
import com.navigation.Destinations
import com.raya_challenge.splash.splashGraph

@Composable
fun App() {
    CoinTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Colors().background
        ) {
            NavHost(
                navController = rememberNavController(),
                startDestination = Destinations.Splash
            ) {
                splashGraph()
            }
        }
    }
}