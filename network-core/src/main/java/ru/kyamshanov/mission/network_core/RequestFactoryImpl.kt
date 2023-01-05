package ru.kyamshanov.mission.network_core

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.serialization.gson.gson

class RequestFactoryImpl : RequestFactory {

    private val client = HttpClient(Android) {
        install(ContentNegotiation) { gson() }
    }

    private val host = "http://192.168.43.29:80"

    override suspend fun get(endpoint: String, block: HttpRequestBuilder.() -> Unit) =
        client.get("$host/$endpoint", block)

    override suspend fun post(endpoint: String, block: HttpRequestBuilder.() -> Unit) =
        client.post("$host/$endpoint", block)
}