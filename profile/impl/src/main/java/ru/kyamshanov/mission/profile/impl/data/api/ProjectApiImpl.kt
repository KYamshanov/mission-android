package ru.kyamshanov.mission.profile.impl.data.api

import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kyamshanov.mission.network_core.api.RequestFactory
import ru.kyamshanov.mission.network_core.api.utils.retrieveBody
import ru.kyamshanov.mission.profile.impl.data.model.GetAttachedProjectsRsDto

internal class ProjectApiImpl @Inject constructor(
    private val requestFactory: RequestFactory,
) : ProjectApi {

    override suspend fun getAttachedProjects(): GetAttachedProjectsRsDto = withContext(Dispatchers.IO) {
        val response = requestFactory.get("/project/private/get/attached") {
            contentType(ContentType.Application.Json)
        }
        response.retrieveBody()
    }
}