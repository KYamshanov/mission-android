package ru.kyamshanov.mission.project_view.impl.domain.usecase

import ru.kyamshanov.mission.project_view.impl.domain.model.ProjectInfo

internal interface GetProjectUseCase {

    suspend fun getProjectById(projectId: String): Result<ProjectInfo>
}