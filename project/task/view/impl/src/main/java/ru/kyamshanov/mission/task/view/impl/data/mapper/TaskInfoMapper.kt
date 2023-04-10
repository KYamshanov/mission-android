package ru.kyamshanov.mission.task.view.impl.data.mapper

import ru.kyamshanov.mission.task.view.impl.data.model.GetTaskRsDto
import ru.kyamshanov.mission.task.view.impl.domain.model.TaskInfo

internal fun GetTaskRsDto.toDomain() = TaskInfo(
    title = title,
    description = text,
    state = taskStage.toDomain(),
    startAt = startAt,
    endAt = endAt,
    maxPoints = maxPoints,
)