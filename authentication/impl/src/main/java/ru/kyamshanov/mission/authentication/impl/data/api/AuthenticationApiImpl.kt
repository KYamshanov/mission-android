package ru.kyamshanov.mission.authentication.impl.data.api

import io.ktor.client.call.body
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import ru.kyamshanov.mission.authentication.impl.data.model.LoginRq
import ru.kyamshanov.mission.authentication.impl.domain.AccessData
import ru.kyamshanov.mission.network_core.RequestFactoryImpl

internal class AuthenticationApiImpl : AuthenticationApi {

    private val requestFactory = RequestFactoryImpl()

    override suspend fun login(login: String, password: CharSequence): Result<AccessData> = runCatching {
        val response = requestFactory.post("auth/login") {
            contentType(ContentType.Application.Json)
            setBody(
                LoginRq(
                    login = login,
                    password = password.toString(),
                    info = mapOf("fingerprint" to "d", "Frontend-type" to "android")
                )
            )
        }
        response.body()
    }
}