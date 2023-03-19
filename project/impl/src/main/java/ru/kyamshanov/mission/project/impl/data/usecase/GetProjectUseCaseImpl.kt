package ru.kyamshanov.mission.project.impl.data.usecase

import javax.inject.Inject
import ru.kyamshanov.mission.project.impl.data.api.ProjectApi
import ru.kyamshanov.mission.project.impl.data.model.toDomain
import ru.kyamshanov.mission.project.impl.domain.model.ProjectInfo
import ru.kyamshanov.mission.project.impl.domain.usecase.GetProjectUseCase
import ru.kyamshanov.mission.session_front.api.SessionInfo
import ru.kyamshanov.mission.session_front.api.model.UserRole

internal class GetProjectUseCaseImpl @Inject constructor(
    private val projectApi: ProjectApi,
    private val sessionInfo: SessionInfo,
) : GetProjectUseCase {

    override suspend fun getProjectById(projectId: String): Result<ProjectInfo> = runCatching {
        val projectInfo = projectApi.getProject(projectId = projectId)
        val participants = runCatching {
            if (sessionInfo.hasRole(UserRole.MANAGER)) projectApi.getManagedTeam(projectId = projectId)
            else projectApi.getTeam(projectId = projectId)
        }.getOrNull()?.participants.orEmpty()

        ProjectInfo(
            id = projectInfo.id,
            title = projectInfo.title,
            description = projectInfo.description,
            participants = participants.map { it.toDomain() }
        )
    }
}