package ru.kyamshanov.mission.project_view.impl.data.usecase

import javax.inject.Inject
import ru.kyamshanov.mission.project.common.domain.model.ProjectId
import ru.kyamshanov.mission.project_view.impl.data.api.ProjectApi
import ru.kyamshanov.mission.project_view.impl.data.model.getStage
import ru.kyamshanov.mission.project_view.impl.data.model.toDomain
import ru.kyamshanov.mission.project_view.impl.domain.model.ProjectInfo
import ru.kyamshanov.mission.project_view.impl.domain.usecase.FetchTeamUseCase
import ru.kyamshanov.mission.project_view.impl.domain.usecase.GetProjectUseCase

internal class GetProjectUseCaseImpl @Inject constructor(
    private val projectApi: ProjectApi,
    private val fetchTeamUseCase: FetchTeamUseCase,
) : GetProjectUseCase {

    override suspend fun getProjectById(projectId: String): Result<ProjectInfo> = runCatching {
        val projectInfo = projectApi.getProject(projectId = projectId)
        val participants = fetchTeamUseCase.fetchTeam(ProjectId(projectId)).getOrDefault(emptyList())

        ProjectInfo(
            id = projectInfo.id,
            title = projectInfo.title,
            description = projectInfo.description,
            participants = participants,
            tasks = projectInfo.tasks.map { it.toDomain() },
            projectStage = projectInfo.getStage(),
        )
    }
}