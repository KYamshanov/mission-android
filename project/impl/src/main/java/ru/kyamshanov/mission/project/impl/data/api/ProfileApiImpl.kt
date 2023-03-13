package ru.kyamshanov.mission.project.impl.data.api

import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kyamshanov.mission.network_core.api.RequestFactory
import ru.kyamshanov.mission.network_core.api.utils.retrieveBody
import ru.kyamshanov.mission.project.impl.data.model.MappingRqDto
import ru.kyamshanov.mission.project.impl.data.model.MappingRsDto

internal class ProfileApiImpl @Inject constructor(
    private val requestFactory: RequestFactory,
) : ProfileApi {

    override suspend fun mappingUsers(body: MappingRqDto): MappingRsDto = withContext(Dispatchers.IO) {
            val response = requestFactory.post("/profile/private/search/map") {
            contentType(ContentType.Application.Json)
            setBody(body)
        }
        response.retrieveBody()
    }
}