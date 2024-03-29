package ru.kyamshanov.mission.network_core.impl

import android.icu.text.SimpleDateFormat
import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.http.HttpHeaders
import io.ktor.serialization.gson.gson
import java.text.DateFormat
import javax.inject.Inject
import ru.kyamshanov.mission.network_core.api.RequestFactory
import ru.kyamshanov.mission.session_front.api.SessionInfo
import ru.kyamshanov.mission.session_front.api.session.JwtLoggedSession
import ru.kyamshanov.mission.session_front.api.session.LoggedSession

class RequestFactoryImpl @Inject constructor(
    private val sessionInfo: SessionInfo,
) : RequestFactory {

    private val client = HttpClient(Android) {
        install(Logging) {
            logger = NetworkLogger()
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            gson { setDateFormat("yyyy-MM-dd'T'hh:mm:ss").create() }
        }
        defaultRequest {
            url("http://192.168.43.29:80/") //mobile internet
           // url("http://10.2.15.91:80/") //wifi
            getAuthorizationHeader()?.let { header(HttpHeaders.Authorization, it) }
            getIdTokenHeader()?.let { header(IDENTIFICATION_HEADER, it) }
        }
    }

    override suspend fun get(endpoint: String, block: HttpRequestBuilder.() -> Unit) =
        client.get(endpoint, block)

    override suspend fun post(endpoint: String, block: HttpRequestBuilder.() -> Unit) =
        client.post(endpoint, block)

    private fun getAuthorizationHeader(): String? =
        (sessionInfo.session as? JwtLoggedSession)?.accessToken

    private fun getIdTokenHeader(): String? =
        (sessionInfo.session as? LoggedSession)?.idToken?.value

    private inner class NetworkLogger : Logger {

        override fun log(message: String) {
            Log.d(LOG_TEG, message)
        }
    }

    private companion object {

        const val LOG_TEG = "Network"

        const val IDENTIFICATION_HEADER = "Mission-id"
    }
}