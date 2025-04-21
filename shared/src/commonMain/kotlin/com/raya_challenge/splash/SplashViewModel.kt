package com.raya_challenge.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raya_challenge.splash.contract.SplashContract
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel(), SplashContract {
    private val _state = MutableStateFlow(SplashContract.State())
    override val state: StateFlow<SplashContract.State> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<SplashContract.Effect>()
    override val effect: SharedFlow<SplashContract.Effect> = _effect.asSharedFlow()

    override fun event(event: SplashContract.Event) = when (event) {
        is SplashContract.Event.NavigateToHome -> onNavigateToHome()
    }

    private fun onNavigateToHome() {
        viewModelScope.launch {
            _effect.emit(SplashContract.Effect.NavigateToHome)
        }
    }
}