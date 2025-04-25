package com.raya_challenge.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.designsystem.extensions.passwordTransformation
import com.domain.models.CryptoType
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import raya_challenge.shared.generated.resources.Res
import raya_challenge.shared.generated.resources.home_card_balance_in_bitcoin_label
import raya_challenge.shared.generated.resources.home_card_balance_in_ethereum_label
import raya_challenge.shared.generated.resources.home_card_balance_label
import raya_challenge.shared.generated.resources.home_symbol_price
import raya_challenge.shared.generated.resources.ic_arrow_right
import raya_challenge.shared.generated.resources.ic_bitcoin
import raya_challenge.shared.generated.resources.ic_ethereum
import raya_challenge.shared.generated.resources.ic_hide_eye
import raya_challenge.shared.generated.resources.ic_show_eye

@Composable
fun CardBalance(
    balance: String,
    balanceInBitcoin: String,
    balanceInEthereum: String,
    showBalance: Boolean,
    onCurrencyType: () -> Unit,
    onConversion: (cryptoType: CryptoType) -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiary),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Text(
                text = stringResource(Res.string.home_card_balance_label),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    text = stringResource(Res.string.home_symbol_price).plus(
                        "${balance.passwordTransformation(!showBalance)}"
                    ),
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 32.sp),
                    color = MaterialTheme.colorScheme.onBackground
                )

                Image(
                    modifier = Modifier
                        .size(28.dp)
                        .padding(end = 4.dp)
                        .clip(CircleShape)
                        .clickable {
                            onCurrencyType.invoke()
                        },
                    painter = painterResource(if (showBalance) Res.drawable.ic_hide_eye else Res.drawable.ic_show_eye),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
                )
            }

            Row(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .shadow(elevation = 4.dp, shape = RoundedCornerShape(30.dp))
                    .background(
                        MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(30.dp)
                    )
                    .padding(4.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .clickable { onConversion.invoke(CryptoType.BTC) },
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(36.dp)
                        .padding(start = 6.dp),
                    painter = painterResource(Res.drawable.ic_bitcoin),
                    contentDescription = "",
                )

                Text(
                    modifier = Modifier.padding(start = 6.dp),
                    text = stringResource(Res.string.home_card_balance_in_bitcoin_label),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 13.sp)
                )

                Text(
                    modifier = Modifier.fillMaxWidth().weight(1f).padding(horizontal = 4.dp),
                    text = stringResource(Res.string.home_symbol_price).plus(
                        "${balanceInBitcoin.passwordTransformation(!showBalance)}"
                    ),
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 13.sp)
                )

                Image(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(Res.drawable.ic_arrow_right),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
                )
            }

            Row(
                modifier = Modifier
                    .padding(top = 6.dp)
                    .fillMaxWidth()
                    .shadow(elevation = 4.dp, shape = RoundedCornerShape(30.dp))
                    .background(
                        MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(30.dp)
                    )
                    .padding(4.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .clickable { onConversion.invoke(CryptoType.ETH) },
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(36.dp)
                        .padding(start = 6.dp),
                    painter = painterResource(Res.drawable.ic_ethereum),
                    contentDescription = "",
                )

                Text(
                    modifier = Modifier.padding(start = 6.dp),
                    text = stringResource(Res.string.home_card_balance_in_ethereum_label),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 13.sp)
                )

                Text(
                    modifier = Modifier.fillMaxWidth().weight(1f).padding(horizontal = 4.dp),
                    text = stringResource(Res.string.home_symbol_price).plus(
                        "${balanceInEthereum.passwordTransformation(!showBalance)}"
                    ),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 13.sp,
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 13.sp)
                )

                Image(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(Res.drawable.ic_arrow_right),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
                )
            }
        }
    }
}