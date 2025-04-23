package com.domain

import com.domain.models.Balance
import com.domain.models.CurrencyType
import com.domain.models.Price
import com.domain.models.Transaction
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getPrice(
        ids: String = "bitcoin,ethereum",
        vsCurrencies: String = "usd,ars"
    ): Flow<Price>

    fun getBalance(currencyType: CurrencyType): Flow<Balance>

    fun getTransactions(): Flow<List<Transaction>>
}