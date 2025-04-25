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

/**
 * Koin module responsible for providing the service layer dependencies.
 *
 * This module configures the dependency injection for the [Repository] interface.
 * It defines a factory that creates an instance of [RepositoryImpl] and exposes it as a [Repository].
 * The [RepositoryImpl] instance is created with a dependency injected from Koin, resolved via `get()`.
 *
 * @property factory Defines a factory function that creates and returns a [Repository] instance.
 * @constructor Creates a new instance of the service module.
 */
val provideServiceModule = module {
    factory { RepositoryImpl(get()) as Repository }
}

/**
 * Koin module responsible for providing a configured [HttpClient] instance.
 *
 * This module defines a singleton [HttpClient] that is configured with:
 * - **Content Negotiation:**  It installs the [ContentNegotiation] plugin to handle JSON serialization/deserialization.
 *   - The JSON configuration ignores unknown keys (`ignoreUnknownKeys = true`).
 *   - It accepts any content type (`contentType = ContentType.Any`).
 * - **Default Request Configuration:** It installs the [DefaultRequest] plugin to set default request parameters:
 *   - **Base URL:** The `host` is set to `BuildKonfig.API_ENDPOINT` and the `protocol` to `HTTPS`.
 *   - **Headers:** It adds a custom header "x-cg-demo-api-key" with the value from `BuildKonfig.API_KEY`.
 *   - **Content Type:** It sets the default content type to `ContentType.Application.Json`.
 *
 * This pre-configured [HttpClient] is then available for injection into other components using Koin.
 */
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