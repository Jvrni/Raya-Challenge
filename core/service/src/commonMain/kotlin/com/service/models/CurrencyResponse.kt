package com.service.models

import com.domain.models.Currency
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyResponse(
    val usd: Double,
    val ars: Double,
) {
    fun toCurrency(): Currency = Currency(
        usd = usd,
        ars = ars
    )
}