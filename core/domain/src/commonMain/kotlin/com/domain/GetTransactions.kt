package com.domain

import com.domain.models.Transaction
import kotlinx.coroutines.flow.Flow

/**
 * `GetTransactions` is a use case class responsible for retrieving a list of transactions.
 *
 * This class encapsulates the logic for fetching transactions from a [Repository].
 * It leverages Kotlin's `Flow` to provide a stream of transaction data, allowing
 * for reactive updates and efficient handling of asynchronous data.
 *
 * @property repository The [Repository] instance used to access transaction data.
 */
class GetTransactions(private val repository: Repository) {

    operator fun invoke(): Flow<List<Transaction>> {
        return repository.getTransactions()
    }
}