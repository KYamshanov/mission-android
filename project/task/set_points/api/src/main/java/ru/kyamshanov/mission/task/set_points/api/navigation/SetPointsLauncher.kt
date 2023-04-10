package ru.kyamshanov.mission.task.set_points.api.navigation

import ru.kyamshanov.mission.project.common.domain.model.TaskId

interface SetPointsLauncher {

    fun launch(taskId: TaskId, maxPoints : Int)
}