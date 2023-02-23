package ru.kyamshanov.mission.creating_project.impl.domain.usecase

import javax.inject.Inject
import ru.kyamshanov.mission.project.api.navigation.ProjectLauncher

interface OpenProjectScreenUseCase {

    fun open(projectId: String)
}

internal class OpenProjectScreenUseCaseImpl @Inject constructor(
    private val projectLauncher: ProjectLauncher,
) : OpenProjectScreenUseCase {

    override fun open(projectId: String) {
        projectLauncher.launch(projectId)
    }
}