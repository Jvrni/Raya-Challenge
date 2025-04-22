package com.raya_challenge.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.navigation.Destinations
import com.raya_challenge.collectInLaunchedEffect
import com.raya_challenge.home.contract.HomeContract
import com.raya_challenge.use
import org.koin.compose.getKoin

fun NavGraphBuilder.homeGraph() {
    composable<Destinations.Home> {
        val viewModel: HomeViewModel = getKoin().get()
        val (state, event, effect) = use(viewModel = viewModel)

        event.invoke(HomeContract.Event.OnStart)

        effect.collectInLaunchedEffect { dispatch ->
        }

        HomeScreen(state, viewModel)
    }
}