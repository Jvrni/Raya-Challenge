package com.raya_challenge.home.contract

import com.domain.models.CurrencyType
import com.domain.models.Transaction
import com.raya_challenge.UnidirectionalViewModel

interface HomeContract : UnidirectionalViewModel<HomeContract.State, HomeContract.Event, HomeContract.Effect> {

    data class State(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val balance: String = "0.0",
        val transactions: List<Transaction> = emptyList(),
        val balanceInBitcoin: String = "0.0",
        val balanceInEthereum: String = "0.0",
        val showBalance: Boolean = true,
        val showBottomSheet: Boolean = false
    )

    sealed class Event {
        data object OnStart : Event()
        data class OnSelectCurrency(val currencyType: CurrencyType) : Event()
        data object OnShowBalance : Event()
        data class ShowBottomSheet(val condition: Boolean = false) : Event()
    }

    sealed class Effect {
    }
}