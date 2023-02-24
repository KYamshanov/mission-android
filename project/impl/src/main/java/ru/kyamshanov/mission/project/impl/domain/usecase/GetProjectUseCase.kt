package ru.kyamshanov.mission.project.impl.domain.usecase

import javax.inject.Inject
import ru.kyamshanov.mission.project.impl.domain.gateway.ProjectGateway
import ru.kyamshanov.mission.project.impl.domain.model.ProjectInfo

internal interface GetProjectUseCase {

    suspend fun getProjectById(projectId: String): Result<ProjectInfo>
}

internal class GetProjectUseCaseImpl @Inject constructor(
    private val projectGateway: ProjectGateway,
) : GetProjectUseCase {

    override suspend fun getProjectById(projectId: String): Result<ProjectInfo> = runCatching {
        projectGateway.getProject(projectId = projectId)
    }
}