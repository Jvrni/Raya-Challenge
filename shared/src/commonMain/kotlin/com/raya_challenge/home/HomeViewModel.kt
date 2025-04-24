package com.raya_challenge.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.domain.GetBalance
import com.domain.GetPrice
import com.domain.GetTransactions
import com.domain.models.CryptoType
import com.domain.models.CurrencyType
import com.raya_challenge.base.NumberFormat
import com.raya_challenge.home.contract.HomeContract
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getPrice: GetPrice,
    private val getBalance: GetBalance,
    private val getTransactions: GetTransactions
) : ViewModel(), HomeContract {
    private val _state = MutableStateFlow(HomeContract.State())
    override val state: StateFlow<HomeContract.State> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<HomeContract.Effect>()
    override val effect: SharedFlow<HomeContract.Effect> = _effect.asSharedFlow()

    override fun event(event: HomeContract.Event) = when (event) {
        is HomeContract.Event.OnStart -> onStart()
        is HomeContract.Event.OnSelectCurrency -> onStart(event.currencyType)
        is HomeContract.Event.OnShowBalance -> onShowBalance()
        is HomeContract.Event.ShowBottomSheet -> onShowBottomSheet(
            event.condition,
            event.currencyType,
            event.cryptoType
        )

        is HomeContract.Event.OnConversionCurrency -> onConversionCurrency(event.value)

        is HomeContract.Event.OnConversionCrypto -> onConversionCrypto(event.value)
    }

    private fun onStart(currencyType: CurrencyType = CurrencyType.USD) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            combine(
                getPrice(),
                getBalance(currencyType),
                getTransactions()
            ) { prices, balance, transactions ->
                val bitcoinPrice = when (currencyType) {
                    CurrencyType.USD -> prices.bitcoin.usd
                    else -> prices.bitcoin.ars
                }

                val ethereumPrice = when (currencyType) {
                    CurrencyType.USD -> prices.ethereum.usd
                    else -> prices.ethereum.ars
                }

                val balanceInBitcoin = balance.current / bitcoinPrice
                val balanceInEthereum = balance.current / ethereumPrice

                HomeContract.State(
                    balance = NumberFormat().formatToString(balance.current),
                    transactions = transactions,
                    balanceInBitcoin = NumberFormat().formatToString(balanceInBitcoin),
                    balanceInEthereum = NumberFormat().formatToString(balanceInEthereum),
                    isLoading = false,
                    currencyType = currencyType,
                    bitcoinPrice = NumberFormat().formatToString(balance.current / balanceInBitcoin),
                    ethereumPrice = NumberFormat().formatToString(balance.current / balanceInEthereum)
                )
            }
                .catch { error ->
                    _state.update { it.copy(isLoading = false, isError = true) }
                }
                .collect { state ->
                    _state.update { state }
                }
        }
    }

    private fun onShowBalance() {
        _state.update { state.value.copy(showBalance = !it.showBalance) }
    }

    private fun onShowBottomSheet(
        condition: Boolean,
        currencyType: CurrencyType,
        cryptoType: CryptoType
    ) {
        _state.update {
            state.value.copy(
                showBottomSheet = condition,
                currencyType = currencyType,
                cryptoType = cryptoType,
                conversionCurrencyPrice = state.value.balance,
                conversionCryptoPrice = when (cryptoType) {
                    CryptoType.BTC -> state.value.balanceInBitcoin
                    else -> state.value.balanceInEthereum
                }
            )
        }
    }

    private fun onConversionCurrency(value: String) {
        val cryptoPryce = when (state.value.cryptoType) {
            CryptoType.BTC -> state.value.bitcoinPrice
            else -> state.value.ethereumPrice
        }

        _state.update {
            it.copy(
                conversionCurrencyPrice = value,
                conversionCryptoPrice = NumberFormat().formatToString(
                    NumberFormat().formatToDouble(value) / NumberFormat().formatToDouble(cryptoPryce)
                )
            )
        }
    }

    private fun onConversionCrypto(value: String) {
        val cryptoPryce = when (state.value.cryptoType) {
            CryptoType.BTC -> state.value.bitcoinPrice
            else -> state.value.ethereumPrice
        }

        _state.update {
            it.copy(
                conversionCurrencyPrice = NumberFormat().formatToString(
                    NumberFormat().formatToDouble(cryptoPryce) * NumberFormat().formatToDouble(value)
                ),
                conversionCryptoPrice = value
            )
        }
    }
}