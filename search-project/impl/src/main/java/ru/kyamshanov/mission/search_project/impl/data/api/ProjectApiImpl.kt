package ru.kyamshanov.mission.search_project.impl.data.api

import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kyamshanov.mission.network_core.api.RequestFactory
import ru.kyamshanov.mission.network_core.api.utils.retrieveBody
import ru.kyamshanov.mission.search_project.impl.data.model.GetAllProjectsRqDto
import ru.kyamshanov.mission.search_project.impl.data.model.GetAllProjectsRsDto

internal class ProjectApiImpl @Inject constructor(
    private val requestFactory: RequestFactory,
) : ProjectApi {

    override suspend fun loadProjects(body: GetAllProjectsRqDto): GetAllProjectsRsDto = withContext(Dispatchers.IO) {
        val response = requestFactory.post("/project/private/get/all") {
            contentType(ContentType.Application.Json)
            setBody(body)
        }
        response.retrieveBody()
    }
}