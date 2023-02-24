package ru.kyamshanov.mission.project.impl.data.api

import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kyamshanov.mission.network_core.api.RequestFactory
import ru.kyamshanov.mission.network_core.api.utils.retrieveBody
import ru.kyamshanov.mission.project.impl.data.model.ProjectInfoRsDto

internal class ProjectApiImpl @Inject constructor(
    private val requestFactory: RequestFactory,
) : ProjectApi {

    override suspend fun getProject(projectId: String): ProjectInfoRsDto = withContext(Dispatchers.IO) {
        requestFactory.get("project/${projectId}") {
            contentType(ContentType.Application.Json)
        }.retrieveBody()
    }
}