package ru.kyamshanov.mission.finding_user.impl.data.api

import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kyamshanov.mission.finding_user.impl.data.model.GetTeamRsDto
import ru.kyamshanov.mission.network_core.api.RequestFactory
import ru.kyamshanov.mission.network_core.api.utils.retrieveBody

internal class ProjectApiImpl @Inject constructor(
    private val requestFactory: RequestFactory,
) : ProjectApi {

    override suspend fun getTeam(projectId: String): GetTeamRsDto = withContext(Dispatchers.IO) {
        val response = requestFactory.get("/project/private/team") {
            contentType(ContentType.Application.Json)
            parameter("project", projectId)
        }
        response.retrieveBody()
    }
}