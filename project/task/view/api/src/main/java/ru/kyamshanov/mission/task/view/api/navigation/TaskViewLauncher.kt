package ru.kyamshanov.mission.task.view.api.navigation

import ru.kyamshanov.mission.project.common.domain.model.ProjectId
import ru.kyamshanov.mission.project.common.domain.model.TaskId

interface TaskViewLauncher {

    fun launch(projectId: ProjectId, projectTitle: String, taskId: TaskId)
}