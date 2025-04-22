package com.raya_challenge.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raya_challenge.home.contract.HomeContract
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel(), HomeContract {
    private val _state = MutableStateFlow(HomeContract.State)
    override val state: StateFlow<HomeContract.State> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<HomeContract.Effect>()
    override val effect: SharedFlow<HomeContract.Effect> = _effect.asSharedFlow()

    override fun event(event: HomeContract.Event) = when (event) {
        is HomeContract.Event.OnStart -> onStart()
    }

    private fun onStart() {
        viewModelScope.launch {
        }
    }
}