package ru.kyamshanov.mission.task.set_points.api.navigation

import ru.kyamshanov.mission.project.common.domain.model.TaskId

interface SetPointsLauncher {

    fun launch(
        projectName: String,
        taskName: String,
        taskId: TaskId,
        maxPoints: Int,
    )
}