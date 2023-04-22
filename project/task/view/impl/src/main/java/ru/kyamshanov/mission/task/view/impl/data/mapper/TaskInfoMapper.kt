package ru.kyamshanov.mission.task.view.impl.data.mapper

import ru.kyamshanov.mission.project.common.domain.model.TaskId
import ru.kyamshanov.mission.task.view.impl.data.model.GetTaskRsDto
import ru.kyamshanov.mission.task.view.impl.domain.model.TaskInfo
import ru.kyamshanov.mission.time.api.MissionDateFormatter

internal fun GetTaskRsDto.toDomain(dateFormatter: MissionDateFormatter) = TaskInfo(
    title = title,
    description = text,
    state = taskStage.toDomain(),
    startAt = startAt,
    endAt = endAt,
    maxPoints = maxPoints,
    taskId = TaskId(id),
    dateFormatter = dateFormatter
)