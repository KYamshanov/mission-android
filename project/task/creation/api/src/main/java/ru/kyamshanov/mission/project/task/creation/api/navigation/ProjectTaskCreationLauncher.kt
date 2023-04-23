package ru.kyamshanov.mission.project.task.creation.api.navigation

import ru.kyamshanov.mission.project.common.domain.model.ProjectId

interface ProjectTaskCreationLauncher {

    fun launch(projectId: ProjectId, projectName: String)
}