package com.raya_challenge.di

import com.raya_challenge.home.HomeViewModel
import com.raya_challenge.splash.SplashViewModel
import org.koin.dsl.module

val provideViewModelModule = module {
    single {
        SplashViewModel()
    }
    single {
        HomeViewModel(get(), get(), get())
    }
}