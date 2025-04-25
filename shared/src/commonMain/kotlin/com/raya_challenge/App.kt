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

/**
 * The root composable function for the application, responsible for setting up the overall UI structure,
 * dependency injection with Koin, theming with CoinTheme, and navigation using Jetpack Compose Navigation.
 *
 * This function acts as the entry point for the Compose UI hierarchy and orchestrates the core components
 * of the application, including dependency injection, theming, and navigation. It utilizes Koin for
 * dependency injection, CoinTheme for applying the custom design system, and Jetpack Compose Navigation
 * for managing the screen transitions.
 *
 * The app is structured with a `NavHost` that contains two navigation graphs: `splashGraph` and `homeGraph`.
 * The `splashGraph` is the initial destination (`Destinations.Splash`), displaying the splash screen,
 * while the `homeGraph` represents the main application flow.
 *
 * @see KoinApplication - used for dependency injection.
 * @see CoinTheme - used for applying the custom theme.
 * @see Surface - used as the root container for the UI.
 * @see NavHost - used for defining the navigation structure.
 * @see rememberNavController - used for creating a navigation controller.
 * @see Modifier - used for modifying composable layouts.
 * @see WindowInsets - used for defining the safe area.
 * @see splashGraph - composable that defines the splash graph.
 * @see homeGraph - composable that defines the home graph.
 * @see appModule - Koin module used for dependency injection.
 */
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