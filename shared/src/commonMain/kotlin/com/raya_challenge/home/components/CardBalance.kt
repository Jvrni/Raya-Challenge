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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.designsystem.extensions.passwordTransformation
import com.designsystem.theme.Colors
import com.domain.models.CryptoType
import com.domain.models.CurrencyType
import org.jetbrains.compose.resources.painterResource
import raya_challenge.shared.generated.resources.Res
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
        colors = CardDefaults.cardColors(containerColor = Colors().background),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Text(
                text = "Balance",
                style = TextStyle(fontWeight = FontWeight.Medium).copy(
                    lineHeightStyle = LineHeightStyle(
                        alignment = LineHeightStyle.Alignment.Center,
                        trim = LineHeightStyle.Trim.Both,
                    )
                ),
                color = Color.Black
            )

            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    text = "$${balance.passwordTransformation(!showBalance)}",
                    style = TextStyle(fontWeight = FontWeight.Bold).copy(
                        lineHeightStyle = LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Center,
                            trim = LineHeightStyle.Trim.Both,
                        )
                    ),
                    fontSize = 32.sp,
                    color = Color.Black
                )

                Image(
                    modifier = Modifier.size(28.dp).padding(end = 4.dp).clickable {
                        onCurrencyType.invoke()
                    },
                    painter = painterResource(if (showBalance) Res.drawable.ic_hide_eye else Res.drawable.ic_show_eye),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(Color.Black)
                )
            }

            Row(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .shadow(elevation = 4.dp, shape = RoundedCornerShape(30.dp))
                    .background(
                        Colors().background,
                        shape = RoundedCornerShape(30.dp)
                    )
                    .padding(4.dp)
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
                    text = "In Bitcoin",
                    color = Colors().onBackground,
                    fontSize = 13.sp,
                    style = TextStyle.Default.copy(
                        lineHeightStyle = LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Center,
                            trim = LineHeightStyle.Trim.Both,
                        )
                    )
                )

                Text(
                    modifier = Modifier.fillMaxWidth().weight(1f).padding(horizontal = 4.dp),
                    text = "$${balanceInBitcoin.passwordTransformation(!showBalance)}",
                    color = Colors().onBackground,
                    fontSize = 13.sp,
                    textAlign = TextAlign.End,
                    style = TextStyle.Default.copy(
                        fontWeight = FontWeight.Bold,
                        lineHeightStyle = LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Center,
                            trim = LineHeightStyle.Trim.Both,
                        )
                    )
                )

                Image(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(Res.drawable.ic_arrow_right),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(Color.Black)
                )
            }

            Row(
                modifier = Modifier
                    .padding(top = 6.dp)
                    .fillMaxWidth()
                    .shadow(elevation = 4.dp, shape = RoundedCornerShape(30.dp))
                    .background(
                        Colors().background,
                        shape = RoundedCornerShape(30.dp)
                    )
                    .padding(4.dp)
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
                    text = "In Ethereum",
                    color = Colors().onBackground,
                    fontSize = 13.sp,
                    style = TextStyle.Default.copy(
                        lineHeightStyle = LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Center,
                            trim = LineHeightStyle.Trim.Both,
                        )
                    )
                )

                Text(
                    modifier = Modifier.fillMaxWidth().weight(1f).padding(horizontal = 4.dp),
                    text = "$${balanceInEthereum.passwordTransformation(!showBalance)}",
                    color = Colors().onBackground,
                    fontSize = 13.sp,
                    textAlign = TextAlign.End,
                    style = TextStyle.Default.copy(
                        fontWeight = FontWeight.Bold,
                        lineHeightStyle = LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Center,
                            trim = LineHeightStyle.Trim.Both,
                        )
                    )
                )

                Image(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(Res.drawable.ic_arrow_right),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(Color.Black)
                )
            }
        }
    }
}