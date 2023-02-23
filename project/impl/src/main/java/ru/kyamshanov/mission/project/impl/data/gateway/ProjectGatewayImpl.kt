package ru.kyamshanov.mission.project.impl.data.gateway

import javax.inject.Inject
import ru.kyamshanov.mission.project.impl.data.api.ProjectApi
import ru.kyamshanov.mission.project.impl.data.model.toDomain
import ru.kyamshanov.mission.project.impl.domain.gateway.ProjectGateway
import ru.kyamshanov.mission.project.impl.domain.model.ProjectInfo

internal class ProjectGatewayImpl @Inject constructor(
    private val projectApi: ProjectApi,
) : ProjectGateway {

    override suspend fun getProject(projectId: String): ProjectInfo =
        projectApi.getProject(projectId = projectId).toDomain()
}