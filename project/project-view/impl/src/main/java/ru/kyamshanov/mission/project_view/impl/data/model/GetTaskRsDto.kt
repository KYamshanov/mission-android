package ru.kyamshanov.mission.project_view.impl.data.model

import java.util.Date
import ru.kyamshanov.mission.project_view.impl.domain.model.TaskInfo

internal data class GetTaskRsDto(
    val id: String,
    val title: String,
    val text: String,
    val createdAt: Date,
    val taskStage: TaskStageDto,
    val startAt: Date,
    val endAt: Date,
    val maxPoints: Int,
    val points: Int,
)

internal fun GetTaskRsDto.toDomain() = TaskInfo(
    id = id,
    title = title,
    description = text,
    taskStage = taskStage.toDomain(startAt, endAt, points),
    startAt = startAt,
    endAt = endAt,
    points = points,
    createdAt = createdAt,
    maxPoints = maxPoints
)