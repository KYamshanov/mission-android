package ru.kyamshanov.mission.creating_project.impl.data.api

import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kyamshanov.mission.creating_project.impl.data.model.AttachTeamRqDto
import ru.kyamshanov.mission.creating_project.impl.data.model.CreateProjectRqDto
import ru.kyamshanov.mission.creating_project.impl.data.model.CreateProjectRsDto
import ru.kyamshanov.mission.network_core.api.RequestFactory
import ru.kyamshanov.mission.network_core.api.utils.retrieveBody

internal class ProjectApiImpl @Inject constructor(
    private val requestFactory: RequestFactory,
) : ProjectApi {

    override suspend fun create(body: CreateProjectRqDto): CreateProjectRsDto = withContext(Dispatchers.IO) {
        val response = requestFactory.post("project/manager/create") {
            contentType(ContentType.Application.Json)
            setBody(body)
        }
        response.retrieveBody()
    }

    override suspend fun attachTeam(body: AttachTeamRqDto): Unit = withContext(Dispatchers.IO) {
        val response = requestFactory.post("project/manager/attach") {
            contentType(ContentType.Application.Json)
            setBody(body)
        }
        response.retrieveBody()
    }
}