package ru.kyamshanov.mission.task.view.impl.data.mapper

import ru.kyamshanov.mission.project.common.domain.model.SubtaskId
import ru.kyamshanov.mission.project.common.domain.model.TaskId
import ru.kyamshanov.mission.project.common.domain.model.UserId
import ru.kyamshanov.mission.task.view.impl.data.model.SubTaskStageDto
import ru.kyamshanov.mission.task.view.impl.data.model.SubtaskDto
import ru.kyamshanov.mission.task.view.impl.data.model.UserInfoDto
import ru.kyamshanov.mission.task.view.impl.domain.model.ResponsibleInfo
import ru.kyamshanov.mission.task.view.impl.domain.model.SubtaskInfo
import ru.kyamshanov.mission.time.api.MissionDateFormatter

internal fun SubtaskDto.toDomain(dateFormatter: MissionDateFormatter) = SubtaskInfo(
    taskId = TaskId(value = taskId),
    title = title,
    description = description,
    startAt = startAt,
    endAt = endAt,
    responsible = responsible.toDomain(),
    dateFormatter = dateFormatter,
    executionResult = executionResult,
    stage = stage.toDomain(),
    subtaskId = SubtaskId(value = id)
)

internal fun UserInfoDto.toDomain() = ResponsibleInfo(
    name = userName,
    id = UserId(userId)
)

internal fun SubTaskStageDto.toDomain(): SubtaskInfo.State = when (this) {
    SubTaskStageDto.CREATED -> SubtaskInfo.State.CREATED
    SubTaskStageDto.IN_WORK -> SubtaskInfo.State.IN_WORK
    SubTaskStageDto.FINISHED -> SubtaskInfo.State.FINISHED
}