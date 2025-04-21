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
import com.raya_challenge.di.appModule
import com.raya_challenge.home.homeGraph
import com.raya_challenge.splash.splashGraph
import org.koin.compose.KoinApplication

@Composable
fun App() {
    KoinApplication(application = { modules(appModule()) }) {
        CoinTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Colors().background
            ) {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Destinations.Splash
                ) {
                    splashGraph(navController)
                    homeGraph()
                }
            }
        }
    }
}