package com.raya_challenge.home

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.navigation.Destinations
import com.raya_challenge.home.contract.HomeContract
import com.raya_challenge.use
import org.koin.compose.getKoin

/**
 * Defines the navigation graph for the Home screen.
 *
 * This function adds a composable destination to the navigation graph for the Home screen.
 * It retrieves the HomeViewModel from Koin, observes the state, and handles events.
 *
 * The composable destination is associated with the [Destinations.Home] route.
 *
 * When navigating to this destination:
 * 1. It fetches the [HomeViewModel] using Koin's dependency injection.
 * 2. It utilizes the `use` function (assumed to be a custom utility for state and event handling)
 *    to obtain the state, event, and an unused third parameter from the view model.
 * 3. It triggers the [HomeContract.Event.OnStart] event when the screen is launched using [LaunchedEffect].
 * 4. It renders the [HomeScreen] composable, passing the state, event, and view model as arguments.
 *
 * The [HomeScreen] is then responsible for displaying the UI based on the provided state and handling user interactions via the event function.
 *
 * @receiver [NavGraphBuilder] The navigation graph builder to add the destination to.
 *
 * @see Destinations.Home
 * @see HomeViewModel
 * @see HomeContract.Event.OnStart
 * @see HomeScreen
 */
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