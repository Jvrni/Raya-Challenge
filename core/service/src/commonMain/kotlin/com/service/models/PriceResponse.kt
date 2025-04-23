package com.service.models

import com.domain.models.Price
import kotlinx.serialization.Serializable

@Serializable
data class PriceResponse(
    val bitcoin: CurrencyResponse,
    val ethereum: CurrencyResponse,
) {
    fun toPrice(): Price = Price(
        bitcoin = bitcoin.toCurrency(),
        ethereum = ethereum.toCurrency(),
    )
}

