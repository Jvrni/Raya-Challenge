package com.domain.di

import com.domain.GetBalance
import com.domain.GetPrice
import com.domain.GetTransactions
import org.koin.dsl.module

val provideDomainModule = module {
    single {
        GetPrice(get())
    }

    single {
        GetBalance(get())
    }

    single {
        GetTransactions(get())
    }
}