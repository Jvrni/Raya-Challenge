package com.raya_challenge.home.contract

import com.domain.models.CryptoType
import com.domain.models.CurrencyType
import com.domain.models.Transaction
import com.raya_challenge.UnidirectionalViewModel

/**
 * Contract for the Home screen, defining the unidirectional data flow.
 *
 * This interface outlines the structure for the Home screen's state, events, and effects,
 * adhering to the unidirectional data flow pattern. It specifies what data the screen
 * displays (State), how the user can interact with the screen (Event), and what side
 * effects can be triggered by the screen (Effect).
 */
interface HomeContract : UnidirectionalViewModel<HomeContract.State, HomeContract.Event, HomeContract.Effect> {

    data class State(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val balance: String = "0.0",
        val transactions: List<Transaction> = emptyList(),
        val balanceInBitcoin: String = "0.0",
        val balanceInEthereum: String = "0.0",
        val showBalance: Boolean = true,
        val showBottomSheet: Boolean = false,
        val currencyType: CurrencyType = CurrencyType.USD,
        val cryptoType: CryptoType = CryptoType.BTC,
        val bitcoinPrice: String = "0.0",
        val ethereumPrice: String = "0.0",
        val conversionCurrencyPrice: String = "0.0",
        val conversionCryptoPrice: String = "0.0",
    )

    sealed class Event {
        data object OnStart : Event()
        data class OnSelectCurrency(val currencyType: CurrencyType) : Event()
        data object OnShowBalance : Event()
        data class ShowBottomSheet(
            val condition: Boolean = false,
            val currencyType: CurrencyType = CurrencyType.USD,
            val cryptoType: CryptoType = CryptoType.BTC
        ) : Event()
        data class OnConversionCurrency(val value: String) : Event()
        data class OnConversionCrypto(val value: String) : Event()
    }

    sealed class Effect {
    }
}