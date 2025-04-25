package com.raya_challenge.splash.contract

import com.raya_challenge.UnidirectionalViewModel

/**
 * Contract for the Splash screen's unidirectional view model.
 *
 * This interface defines the states, events, and effects associated with the Splash screen.
 * It follows the Unidirectional Data Flow (UDF) pattern.
 */
interface SplashContract : UnidirectionalViewModel<SplashContract.State, SplashContract.Event, SplashContract.Effect> {

    data class State(
        val delay: Long = 1000,
        val animationDuration: Int = 800,
        val initialOffsetX: Int = 700,
        val targetOffsetX: Int = -700
    )

    sealed class Event {
        data object NavigateToHome : Event()
    }

    sealed class Effect {
        data object NavigateToHome : Effect()
    }
}