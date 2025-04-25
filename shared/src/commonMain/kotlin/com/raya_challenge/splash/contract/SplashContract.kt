package com.raya_challenge.splash.contract

import com.raya_challenge.UnidirectionalViewModel

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