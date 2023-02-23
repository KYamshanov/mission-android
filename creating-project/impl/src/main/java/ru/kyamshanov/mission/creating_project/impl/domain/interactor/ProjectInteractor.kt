package ru.kyamshanov.mission.creating_project.impl.domain.interactor

import ru.kyamshanov.mission.creating_project.impl.domain.gateway.ProjectGateway
import ru.kyamshanov.mission.creating_project.impl.domain.models.CreatingProjectInfo
import ru.kyamshanov.mission.creating_project.impl.domain.models.ProjectInfo
import ru.kyamshanov.mission.creating_project.impl.domain.usecase.OpenProjectScreenUseCase
import javax.inject.Inject

internal interface ProjectInteractor {

    suspend fun createAndOpenProject(projectInfo: CreatingProjectInfo): Result<ProjectInfo>
}

internal class ProjectInteractorImpl @Inject constructor(
    private val projectGateway: ProjectGateway,
    private val openProjectScreenUseCase: OpenProjectScreenUseCase
) : ProjectInteractor {

    override suspend fun createAndOpenProject(projectInfo: CreatingProjectInfo): Result<ProjectInfo> = runCatching {
        val createdProject = projectGateway.createProject(projectInfo)
        openProjectScreenUseCase.open()
        createdProject
    }
}