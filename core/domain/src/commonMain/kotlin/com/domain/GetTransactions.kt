package com.domain

import com.domain.models.Transaction
import kotlinx.coroutines.flow.Flow

class GetTransactions(private val repository: Repository) {

    operator fun invoke(): Flow<List<Transaction>> {
        return repository.getTransactions()
    }
}