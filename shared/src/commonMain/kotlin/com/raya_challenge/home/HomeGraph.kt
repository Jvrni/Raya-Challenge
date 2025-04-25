package com.raya_challenge.home

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.navigation.Destinations
import com.raya_challenge.home.contract.HomeContract
import com.raya_challenge.use
import org.koin.compose.getKoin

fun NavGraphBuilder.homeGraph() {
    composable<Destinations.Home> {
        val viewModel: HomeViewModel = getKoin().get()
        val (state, event, _) = use(viewModel = viewModel)

        LaunchedEffect(Unit) {
            event.invoke(HomeContract.Event.OnStart)
        }

        HomeScreen(state, event, viewModel)
    }
}