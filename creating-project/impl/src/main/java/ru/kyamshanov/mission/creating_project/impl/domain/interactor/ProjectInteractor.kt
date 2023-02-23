package ru.kyamshanov.mission.creating_project.impl.domain.interactor

import javax.inject.Inject
import ru.kyamshanov.mission.creating_project.impl.domain.gateway.ProjectGateway
import ru.kyamshanov.mission.creating_project.impl.domain.models.CreatingProjectInfo
import ru.kyamshanov.mission.creating_project.impl.domain.usecase.OpenProjectScreenUseCase

internal interface ProjectInteractor {

    suspend fun createAndOpenProject(projectInfo: CreatingProjectInfo): Result<Unit>
}

internal class ProjectInteractorImpl @Inject constructor(
    private val projectGateway: ProjectGateway,
    private val openProjectScreenUseCase: OpenProjectScreenUseCase,
) : ProjectInteractor {

    override suspend fun createAndOpenProject(projectInfo: CreatingProjectInfo): Result<Unit> = runCatching {
        projectGateway.createProject(projectInfo).run {
            openProjectScreenUseCase.open(id)
        }
    }
}