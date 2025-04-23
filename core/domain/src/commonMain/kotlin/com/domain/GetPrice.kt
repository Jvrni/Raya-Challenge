package com.domain

import com.domain.models.Price
import kotlinx.coroutines.flow.Flow

class GetPrice(private val repository: Repository) {

    operator fun invoke(): Flow<Price> {
        return repository.getPrice()
    }
}