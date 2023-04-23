package ru.kyamshanov.mission.task.view.api.navigation

import ru.kyamshanov.mission.project.common.domain.model.TaskId

interface TaskViewLauncher {

    fun launch(projectTitle: String, taskId: TaskId)
}