package com.raya_challenge.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.navigation.Destinations

fun NavGraphBuilder.homeGraph() {
    composable<Destinations.Home> {
        HomeScreen()
    }
}