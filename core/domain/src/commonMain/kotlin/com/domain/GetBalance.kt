package com.domain

import com.domain.models.Balance
import com.domain.models.CurrencyType
import kotlinx.coroutines.flow.Flow

/**
 * `GetBalance` is a Use Case class responsible for retrieving the balance of a specific currency.
 *
 * This class acts as an intermediary between the presentation layer (e.g., ViewModel) and the data layer
 * (e.g., Repository). It encapsulates the logic for obtaining the balance and provides a clean interface
 * for the presentation layer to interact with.
 *
 * @property repository The [Repository] instance used to access the data layer and retrieve the balance.
 */
class GetBalance(private val repository: Repository) {

    operator fun invoke(currencyType: CurrencyType): Flow<Balance> {
        return repository.getBalance(currencyType)
    }
}