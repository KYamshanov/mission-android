package ru.kyamshanov.mission.network_core

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpResponse

interface RequestFactory {

    suspend fun get(endpoint: String, block: HttpRequestBuilder.() -> Unit = {}): HttpResponse

    suspend fun post(endpoint: String, block: HttpRequestBuilder.() -> Unit = {}): HttpResponse
}