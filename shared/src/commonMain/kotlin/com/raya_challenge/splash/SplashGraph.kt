package com.raya_challenge.splash

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.navigation.Destinations
import com.raya_challenge.collectInLaunchedEffect
import com.raya_challenge.splash.contract.SplashContract
import com.raya_challenge.use
import org.koin.compose.getKoin

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