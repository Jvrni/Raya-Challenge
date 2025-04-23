package com.raya_challenge.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raya_challenge.home.components.CardBalance
import com.raya_challenge.home.components.CardTransaction
import com.designsystem.theme.Colors
import com.domain.models.CryptoType
import com.domain.models.CurrencyType
import com.raya_challenge.home.components.ConversionBottomSheet
import com.raya_challenge.home.components.ConversionBottomSheetEntity
import com.raya_challenge.home.components.HeaderOptions
import com.raya_challenge.home.contract.HomeContract
import raya_challenge.shared.generated.resources.Res
import raya_challenge.shared.generated.resources.ic_ars_flag
import raya_challenge.shared.generated.resources.ic_bitcoin
import raya_challenge.shared.generated.resources.ic_ethereum
import raya_challenge.shared.generated.resources.ic_usd_flag

@Composable
fun HomeScreen(
    state: HomeContract.State,
    event: (HomeContract.Event) -> Unit,
    viewModel: HomeViewModel
) {
    Scaffold(
        modifier = Modifier.background(Colors().background),
        topBar = {
            HeaderOptions(modifier = Modifier.padding(horizontal = 16.dp)) { currencyType ->
                event.invoke(HomeContract.Event.OnSelectCurrency(currencyType))
            }
        }
    ) { paddingValues ->
        when {
            state.isLoading -> {
            }

            state.isError -> {
            }

            else -> Content(modifier = Modifier.padding(paddingValues), event, state)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(
    modifier: Modifier,
    event: (HomeContract.Event) -> Unit,
    state: HomeContract.State
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Colors().background)
            .padding(16.dp),
    ) {
        item {
            CardBalance(
                state.balance,
                state.balanceInBitcoin,
                state.balanceInEthereum,
                state.showBalance,
                onCurrencyType = { event.invoke(HomeContract.Event.OnShowBalance) },
                onConversion = {
                    event.invoke(
                        HomeContract.Event.ShowBottomSheet(
                            true,
                            state.currencyType,
                            it
                        )
                    )
                }
            )
        }

        item {
            Text(
                modifier = Modifier.padding(top = 30.dp, bottom = 8.dp),
                text = "Transactions",
                style = TextStyle(fontWeight = FontWeight.SemiBold).copy(
                    lineHeightStyle = LineHeightStyle(
                        alignment = LineHeightStyle.Alignment.Center,
                        trim = LineHeightStyle.Trim.Both,
                    )
                ),
                color = Color.Black,
                fontSize = 22.sp,
            )
        }

        items(state.transactions) { CardTransaction(it, state.showBalance) }
    }

    if (state.showBottomSheet)
        ConversionBottomSheet(
            entity = ConversionBottomSheetEntity(
                currency = "1",
                currencyIcon = when (state.currencyType) {
                    CurrencyType.USD -> Res.drawable.ic_usd_flag
                    else -> Res.drawable.ic_ars_flag
                },
                toCurrency = state.balanceInBitcoin, // TODO fix
                toCurrencyIcon = when (state.cryptoType) {
                    CryptoType.BTC -> Res.drawable.ic_bitcoin
                    else -> Res.drawable.ic_ethereum
                },
            ),
            modalBottomSheetState = rememberModalBottomSheetState(),
            onDismissRequest = { event.invoke(HomeContract.Event.ShowBottomSheet(false)) }
        )
}
