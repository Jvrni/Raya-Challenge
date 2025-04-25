package com.raya_challenge

import com.domain.GetBalance
import com.domain.GetPrice
import com.domain.GetTransactions
import com.domain.Repository
import com.domain.models.Balance
import com.domain.models.CryptoType
import com.domain.models.Currency
import com.domain.models.CurrencyType
import com.domain.models.Price
import com.domain.models.Transaction
import com.raya_challenge.home.HomeViewModel
import com.raya_challenge.home.contract.HomeContract
import dev.mokkery.MockMode
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private val repository = mock<Repository>(MockMode.strict)
    private val getPrice = GetPrice(repository)
    private val getBalance = GetBalance(repository)
    private val getTransactions = GetTransactions(repository)

    private val viewModel = HomeViewModel(getPrice, getBalance, getTransactions)

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `onStart should update state with correct balance and prices`() = runTest {
        // Given
        val fakePrices = Price(
            bitcoin = Currency(usd = 50000.0, ars = 10000000.0),
            ethereum = Currency(usd = 2500.0, ars = 500000.0)
        )

        val fakeBalance = Balance(current = 100000.0)
        val fakeTransactions = listOf(Transaction("Google","100.0", "2023-01-01" ))

        everySuspend { repository.getPrice() } returns flowOf(fakePrices)
        everySuspend { repository.getBalance(CurrencyType.USD) } returns flowOf(fakeBalance)
        everySuspend { repository.getTransactions() } returns flowOf(fakeTransactions)

        // When
        viewModel.event(HomeContract.Event.OnStart)

        // Then
        val state = viewModel.state.value
        assertEquals("100,000", state.balance)
        assertEquals("2", state.balanceInBitcoin)
        assertEquals("40", state.balanceInEthereum)
        assertEquals(false, state.isLoading)
        assertEquals(fakeTransactions, state.transactions)
    }

    @Test
    fun `onShowBalance should update state with not show balance`() = runTest {
        // Given

        // When
        viewModel.event(HomeContract.Event.OnShowBalance)

        // Then
        val state = viewModel.state.value
        assertEquals(false, state.showBalance)
    }

    @Test
    fun `onShowBalance should update state with show balance`() = runTest {
        // Given

        // When
        viewModel.event(HomeContract.Event.OnShowBalance)
        viewModel.event(HomeContract.Event.OnShowBalance)

        // Then
        val state = viewModel.state.value
        assertEquals(true, state.showBalance)
    }

    @Test
    fun `oShowBottomSheet should update state with show bottom sheet`() = runTest {
        // Given

        // When
        viewModel.event(HomeContract.Event.ShowBottomSheet(true, CurrencyType.USD, CryptoType.BTC))

        // Then
        val state = viewModel.state.value
        assertEquals(true, state.showBottomSheet)
    }

    @Test
    fun `onConversionCrypto should update state with price converted`() = runTest {
        // Given

        // When
        viewModel.event(HomeContract.Event.OnConversionCrypto("20.0"))

        // Then
        val state = viewModel.state.value
        assertEquals("20.0", state.conversionCryptoPrice)
        assertEquals("0", state.conversionCurrencyPrice)
    }

    @Test
    fun `onSelectCurrency with USD should update state with price correct`() = runTest {
        // Given
        val fakePrices = Price(
            bitcoin = Currency(usd = 50000.0, ars = 10000000.0),
            ethereum = Currency(usd = 2500.0, ars = 500000.0)
        )

        val fakeBalance = Balance(current = 100000.0)
        val fakeTransactions = listOf(Transaction("Google","100.0", "2023-01-01" ))

        everySuspend { repository.getPrice() } returns flowOf(fakePrices)
        everySuspend { repository.getBalance(CurrencyType.USD) } returns flowOf(fakeBalance)
        everySuspend { repository.getTransactions() } returns flowOf(fakeTransactions)

        // When
        viewModel.event(HomeContract.Event.OnSelectCurrency(CurrencyType.USD))

        // Then
        val state = viewModel.state.value
        assertEquals("100,000", state.balance)
        assertEquals("2", state.balanceInBitcoin)
        assertEquals("40", state.balanceInEthereum)
        assertEquals(false, state.isLoading)
        assertEquals(fakeTransactions, state.transactions)
    }

    @Test
    fun `onSelectCurrency with ARS should update state with price correct`() = runTest {
        // Given
        val fakePrices = Price(
            bitcoin = Currency(usd = 50000.0, ars = 10000000.0),
            ethereum = Currency(usd = 2500.0, ars = 500000.0)
        )

        val fakeBalance = Balance(current = 100000.0)
        val fakeTransactions = listOf(Transaction("Google","100.0", "2023-01-01" ))

        everySuspend { repository.getPrice() } returns flowOf(fakePrices)
        everySuspend { repository.getBalance(CurrencyType.ARS) } returns flowOf(fakeBalance)
        everySuspend { repository.getTransactions() } returns flowOf(fakeTransactions)

        // When
        viewModel.event(HomeContract.Event.OnSelectCurrency(CurrencyType.ARS))

        // Then
        val state = viewModel.state.value
        assertEquals("100,000", state.balance)
        assertEquals("0.01", state.balanceInBitcoin)
        assertEquals("0.2", state.balanceInEthereum)
        assertEquals(false, state.isLoading)
        assertEquals(fakeTransactions, state.transactions)
    }
}