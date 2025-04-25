package com.raya_challenge

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.designsystem.theme.CoinTheme
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
                color = MaterialTheme.colorScheme.background
            ) {
                val navController = rememberNavController()

                NavHost(
                    modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing),
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