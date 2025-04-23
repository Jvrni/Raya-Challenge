package com.domain

import com.domain.models.Balance
import com.domain.models.CurrencyType
import kotlinx.coroutines.flow.Flow

class GetBalance(private val repository: Repository) {

    operator fun invoke(currencyType: CurrencyType): Flow<Balance> {
        return repository.getBalance(currencyType)
    }
}