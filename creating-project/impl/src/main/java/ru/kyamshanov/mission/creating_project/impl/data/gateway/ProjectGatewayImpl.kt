package ru.kyamshanov.mission.creating_project.impl.data.gateway

import ru.kyamshanov.mission.creating_project.impl.data.api.ProjectApi
import ru.kyamshanov.mission.creating_project.impl.data.model.CreateProjectRqDto
import ru.kyamshanov.mission.creating_project.impl.data.model.toDomain
import ru.kyamshanov.mission.creating_project.impl.domain.gateway.ProjectGateway
import ru.kyamshanov.mission.creating_project.impl.domain.models.CreatingProjectInfo
import ru.kyamshanov.mission.creating_project.impl.domain.models.CreatedProjectInfo
import javax.inject.Inject

internal class ProjectGatewayImpl @Inject constructor(
    private val projectApi: ProjectApi
) : ProjectGateway {

    override suspend fun createProject(projectInfo: CreatingProjectInfo): CreatedProjectInfo =
        projectApi.create(
            CreateProjectRqDto(
                title = projectInfo.name,
                description = projectInfo.description
            )
        ).toDomain()
}