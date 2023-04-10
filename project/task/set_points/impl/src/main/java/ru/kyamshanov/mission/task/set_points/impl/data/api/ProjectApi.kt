package ru.kyamshanov.mission.task.set_points.impl.data.api

import ru.kyamshanov.mission.task.set_points.impl.data.model.SetTaskPointsRqDto

internal interface ProjectApi {

    suspend fun setTaskPoint(body: SetTaskPointsRqDto)
}