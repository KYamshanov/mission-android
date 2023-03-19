package ru.kyamshanov.mission.project.impl.data.api

import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kyamshanov.mission.network_core.api.RequestFactory
import ru.kyamshanov.mission.network_core.api.utils.retrieveBody
import ru.kyamshanov.mission.project.impl.data.model.GetTeamRsDto
import ru.kyamshanov.mission.project.impl.data.model.ProjectInfoDto

internal class ProjectApiImpl @Inject constructor(
    private val requestFactory: RequestFactory,
) : ProjectApi {

    override suspend fun getProject(projectId: String): ProjectInfoDto = withContext(Dispatchers.IO) {
        requestFactory.get("/project/private/find?id=${projectId}") {
            contentType(ContentType.Application.Json)
        }.retrieveBody()
    }

    override suspend fun getTeam(projectId: String): GetTeamRsDto = withContext(Dispatchers.IO) {
        requestFactory.get("/project/private/team?project=${projectId}") {
            contentType(ContentType.Application.Json)
        }.retrieveBody()
    }

    override suspend fun getManagedTeam(projectId: String): GetTeamRsDto = withContext(Dispatchers.IO) {
        requestFactory.get("/project/manager/team?project=${projectId}") {
            contentType(ContentType.Application.Json)
        }.retrieveBody()
    }
}