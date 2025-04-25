package com.raya_challenge.di

import com.domain.di.provideDomainModule
import com.service.di.provideHttpClientModule
import com.service.di.provideServiceModule

fun appModule() = listOf(
    provideViewModelModule,
    provideDomainModule,
    provideServiceModule,
    provideHttpClientModule
)