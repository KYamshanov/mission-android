package ru.kyamshanov.mission.session_front.impl.data.api

import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kyamshanov.mission.network_core.api.RequestFactory
import ru.kyamshanov.mission.network_core.api.utils.retrieveBody
import ru.kyamshanov.mission.session_front.impl.data.model.IdentifyRsDto

internal class IdentifyApiImpl @Inject constructor(
    private val requestFactory: RequestFactory,
) : IdentifyApi {

    override suspend fun identify(): IdentifyRsDto = withContext(Dispatchers.IO) {
        val response = requestFactory.post("private/id/mission/identify") {
            contentType(ContentType.Application.Json)
        }
        response.retrieveBody()
    }
}