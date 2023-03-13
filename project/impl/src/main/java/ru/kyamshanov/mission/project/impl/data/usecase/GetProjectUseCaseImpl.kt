package ru.kyamshanov.mission.project.impl.data.usecase

import javax.inject.Inject
import ru.kyamshanov.mission.project.impl.data.api.ProfileApi
import ru.kyamshanov.mission.project.impl.data.api.ProjectApi
import ru.kyamshanov.mission.project.impl.data.model.toDomain
import ru.kyamshanov.mission.project.impl.domain.model.ProjectInfo
import ru.kyamshanov.mission.project.impl.domain.usecase.GetProjectUseCase

internal class GetProjectUseCaseImpl @Inject constructor(
    private val projectApi: ProjectApi,
    private val profileApi: ProfileApi,
) : GetProjectUseCase {

    override suspend fun getProjectById(projectId: String): Result<ProjectInfo> = runCatching {
        val projectInfo = projectApi.getProject(projectId = projectId)
        val participants = profileApi.mappingUsers(projectInfo.participants.map { it.userId }).toDomain()
        ProjectInfo(
            id = projectInfo.id,
            title = projectInfo.title,
            description = projectInfo.description,
            participants = participants
        )
    }
}