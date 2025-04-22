package com.raya_challenge.home.contract

import com.raya_challenge.UnidirectionalViewModel

interface HomeContract  : UnidirectionalViewModel<HomeContract.State, HomeContract.Event, HomeContract.Effect> {

    data object State

    sealed class Event {
        data object OnStart : Event()
    }

    sealed class Effect {
    }
}