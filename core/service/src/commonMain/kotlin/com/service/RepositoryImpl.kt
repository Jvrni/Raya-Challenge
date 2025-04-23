package com.service

import com.domain.Repository
import com.domain.models.Balance
import com.domain.models.CurrencyType
import com.domain.models.Price
import com.domain.models.Transaction
import com.service.models.PriceResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RepositoryImpl(private val httpClient: HttpClient) : Repository {

    override fun getPrice(ids: String, vsCurrencies: String): Flow<Price> {
        return flow {
            val response = httpClient.get("/api/v3/simple/price?ids=$ids&vs_currencies=$vsCurrencies")
                .body<PriceResponse>()
            emit(response.toPrice())
        }.flowOn(Dispatchers.IO)
    }

    override fun getBalance(currencyType: CurrencyType): Flow<Balance> {
        return flow {
            emit(
                when (currencyType) {
                    CurrencyType.USD -> Balance(current = 20000.0)
                    CurrencyType.ARS -> Balance(current = 10000.0)
                }
            )
        }
    }

    override fun getTransactions(): Flow<List<Transaction>> {
        return flow {
            emit(
                listOf(
                    Transaction(
                        title = "Google",
                        value = "100.0",
                        date = "07/02"
                    ),
                    Transaction(
                        title = "Apple",
                        value = "40.0",
                        date = "05/02"
                    ),
                    Transaction(
                        title = "Amazon",
                        value = "70.0",
                        date = "03/02"
                    ),
                    Transaction(
                        title = "Store",
                        value = "302.0",
                        date = "01/02"
                    )
                )
            )
        }
    }
}