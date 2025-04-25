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
import org.jetbrains.compose.resources.stringResource
import raya_challenge.shared.generated.resources.Res
import raya_challenge.shared.generated.resources.coin_black
import raya_challenge.shared.generated.resources.coin_light
import raya_challenge.shared.generated.resources.home_header_ars_label
import raya_challenge.shared.generated.resources.home_header_usd_label
import raya_challenge.shared.generated.resources.ic_ars_flag
import raya_challenge.shared.generated.resources.ic_usd_flag

/**
 * Displays the header options for the main screen, including a coin icon and a currency selector.
 *
 * This composable shows a row containing:
 *  - A coin icon that changes based on the system's dark theme setting.
 *  - An `OptionsSelect` component that allows the user to choose between different currencies (USD and ARS in this example).
 *
 * @param modifier The modifier to be applied to the row.
 * @param action A lambda function that is invoked when a currency option is selected.
 *               It receives the selected [CurrencyType] as a parameter.
 *
 * Example Usage:
 * ```
 * HeaderOptions(
 *     modifier = Modifier.fillMaxWidth(),
 *     action = { currency ->
 *         println("Selected currency: $currency")
 *         // Perform actions based on the selected currency
 *     }
 * )
 * ```
 */
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
                    text = stringResource(Res.string.home_header_usd_label),
                    icon = Res.drawable.ic_usd_flag,
                    onClick = { action.invoke(CurrencyType.USD) }
                ),
                OptionSelectEntity(
                    currencyType = CurrencyType.ARS.name,
                    text = stringResource(Res.string.home_header_ars_label),
                    icon = Res.drawable.ic_ars_flag,
                    onClick = { action.invoke(CurrencyType.ARS) }
                )
            )
        )
    }
}