package com.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Destinations {

    @Serializable
    data object Splash : Destinations()
}
