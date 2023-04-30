package ru.kyamshanov.mission.project_view.impl.ui.model

import ru.kyamshanov.mission.project.common.domain.model.TaskId
import ru.kyamshanov.mission.project_view.impl.domain.model.SlimTaskInfo
import ru.kyamshanov.mission.project_view.impl.domain.model.TaskPointsInfo

internal fun SlimTaskInfo.toStagePointInfo() = TaskPointsInfo(
    maxPoints = maxPoints,
    currentPoints = points,
    taskId = TaskId(id),
    taskTitle = title
)