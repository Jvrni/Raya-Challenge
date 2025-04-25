package com.domain

import com.domain.models.Price
import kotlinx.coroutines.flow.Flow

/**
 * Retrieves the current price from the repository.
 *
 * This class is a Use Case responsible for fetching the price data. It encapsulates the
 * interaction with the [Repository] and exposes a [Flow] of [Price] objects.
 *
 * @property repository The [Repository] instance used to access the price data.
 */
class GetPrice(private val repository: Repository) {

    operator fun invoke(): Flow<Price> {
        return repository.getPrice()
    }
}