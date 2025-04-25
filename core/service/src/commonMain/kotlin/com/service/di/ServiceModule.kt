package com.service.di

import com.domain.Repository
import com.service.BuildKonfig
import com.service.RepositoryImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val provideServiceModule = module {
    factory { RepositoryImpl(get()) as Repository }
}

val provideHttpClientModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    json = Json { ignoreUnknownKeys = true },
                    contentType = ContentType.Any
                )
            }

            install(DefaultRequest){
                url {
                    host = BuildKonfig.API_ENDPOINT
                    protocol = URLProtocol.HTTPS
                    headers {
                        append("x-cg-demo-api-key",BuildKonfig.API_KEY)
                    }
                    contentType(ContentType.Application.Json)
                }
            }
        }
    }
}