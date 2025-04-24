package com.raya_challenge.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.designsystem.components.OptionSelectEntity
import com.designsystem.components.OptionsSelect
import com.domain.models.CurrencyType
import org.jetbrains.compose.resources.painterResource
import raya_challenge.shared.generated.resources.Res
import raya_challenge.shared.generated.resources.coin_black
import raya_challenge.shared.generated.resources.coin_light
import raya_challenge.shared.generated.resources.ic_ars_flag
import raya_challenge.shared.generated.resources.ic_usd_flag

@Composable
fun HeaderOptions(modifier: Modifier, action: (currencyType: CurrencyType) -> Unit) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            modifier = Modifier.size(56.dp),
            painter = painterResource(
                if (isSystemInDarkTheme()) Res.drawable.coin_light
                else Res.drawable.coin_black
            ),
            contentDescription = ""
        )

        OptionsSelect(
            entities = listOf(
                OptionSelectEntity(
                    currencyType = CurrencyType.USD.name,
                    text = "USD",
                    icon = Res.drawable.ic_usd_flag,
                    onClick = { action.invoke(CurrencyType.USD) }
                ),
                OptionSelectEntity(
                    currencyType = CurrencyType.ARS.name,
                    text = "ARS",
                    icon = Res.drawable.ic_ars_flag,
                    onClick = { action.invoke(CurrencyType.ARS) }
                )
            )
        )
    }
}