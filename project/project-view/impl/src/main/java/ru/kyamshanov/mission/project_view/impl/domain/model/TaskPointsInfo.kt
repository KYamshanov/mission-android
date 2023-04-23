package ru.kyamshanov.mission.project_view.impl.domain.model

import ru.kyamshanov.mission.project.common.domain.model.TaskId

internal data class TaskPointsInfo(
    val taskId: TaskId,
    val taskTitle: String,
    val currentPoints: Int?,
    val maxPoints: Int,
) : java.io.Serializable
