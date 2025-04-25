package com.raya_challenge.splash

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.navigation.Destinations
import com.raya_challenge.collectInLaunchedEffect
import com.raya_challenge.splash.contract.SplashContract
import com.raya_challenge.use
import org.koin.compose.getKoin

/**
 * Builds the navigation graph for the Splash screen.
 *
 * This function defines the route and composable content for the splash screen within the overall
 * navigation graph. It also handles navigation to the next screen (Home) based on events from the
 * [SplashViewModel].
 *
 * @param navController The [NavController] responsible for navigating between composables.
 *
 * @see Destinations.Splash
 * @see SplashViewModel
 * @see SplashContract.Effect.NavigateToHome
 * @see SplashScreen
 * @see NavGraphBuilder
 * @see composable
 * @see NavController
 */
fun NavGraphBuilder.splashGraph(navController: NavController) {
    composable<Destinations.Splash> {
        val viewModel: SplashViewModel = getKoin().get()
        val (state, event, effect) = use(viewModel = viewModel)

        effect.collectInLaunchedEffect { dispatch ->
            when (dispatch) {
                is SplashContract.Effect.NavigateToHome -> navController.navigate(Destinations.Home)
            }
        }

        SplashScreen(state, event, viewModel)
    }
}