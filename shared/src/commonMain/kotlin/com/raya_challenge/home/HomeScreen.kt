package com.raya_challenge.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raya_challenge.home.components.CardBalance
import com.raya_challenge.home.components.CardTransaction
import com.domain.models.CryptoType
import com.domain.models.CurrencyType
import com.raya_challenge.home.components.ConversionBottomSheet
import com.raya_challenge.home.components.ConversionBottomSheetEntity
import com.raya_challenge.home.components.HeaderOptions
import com.raya_challenge.home.contract.HomeContract
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import raya_challenge.shared.generated.resources.Res
import raya_challenge.shared.generated.resources.error_state
import raya_challenge.shared.generated.resources.home_error_button
import raya_challenge.shared.generated.resources.home_error_description
import raya_challenge.shared.generated.resources.home_error_title
import raya_challenge.shared.generated.resources.home_transaction_label
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
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        topBar = {
            HeaderOptions(modifier = Modifier.padding(horizontal = 16.dp)) { currencyType ->
                event.invoke(HomeContract.Event.OnSelectCurrency(currencyType))
            }
        }
    ) { paddingValues ->
        when {
            state.isLoading -> {
                Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

            state.isError -> {
                Column(
                    modifier = Modifier.fillMaxSize().padding(paddingValues),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(Res.drawable.error_state),
                        contentDescription = "",
                        modifier = Modifier.size(200.dp)
                    )

                    Text(
                        modifier = Modifier.padding(top = 16.dp),
                        text = stringResource(Res.string.home_error_title),
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Text(
                        modifier = Modifier.padding(top = 16.dp),
                        text = stringResource(Res.string.home_error_description),
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodySmall
                    )

                    Button(
                        modifier = Modifier.padding(top = 24.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)),
                        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 4.dp),
                        onClick = { event.invoke(HomeContract.Event.OnSelectCurrency(state.currencyType)) }
                    ) {
                        Text(
                            modifier = Modifier.padding(4.dp),
                            text = stringResource(Res.string.home_error_button),
                            color = MaterialTheme.colorScheme.onTertiary,
                            textAlign = TextAlign.Center
                        )
                    }
                }
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
            .background(MaterialTheme.colorScheme.background)
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
                text = stringResource(Res.string.home_transaction_label),
                style = MaterialTheme.typography.titleMedium.copy(fontSize = 22.sp),
                color = MaterialTheme.colorScheme.onBackground,
            )
        }

        items(state.transactions) { CardTransaction(it, state.showBalance) }
    }

    if (state.showBottomSheet)
        ConversionBottomSheet(
            entity = ConversionBottomSheetEntity(
                currencyValue = state.conversionCurrencyPrice,
                currencyIcon = when (state.currencyType) {
                    CurrencyType.USD -> Res.drawable.ic_usd_flag
                    else -> Res.drawable.ic_ars_flag
                },
                cryptoValue = state.conversionCryptoPrice,
                toCryptoIcon = when (state.cryptoType) {
                    CryptoType.BTC -> Res.drawable.ic_bitcoin
                    else -> Res.drawable.ic_ethereum
                },
            ),
            modalBottomSheetState = rememberModalBottomSheetState(),
            onDismissRequest = { event.invoke(HomeContract.Event.ShowBottomSheet(false)) },
            onChangeCurrency = { value ->
                event.invoke(HomeContract.Event.OnConversionCurrency(value))
            },
            onChangeCrypto = { value ->
                event.invoke(HomeContract.Event.OnConversionCrypto(value))
            }
        )
}
