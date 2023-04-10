package ru.kyamshanov.mission.project_view.impl.data.model

import java.util.Date
import ru.kyamshanov.mission.project_view.impl.domain.model.SlimTaskInfo

internal data class SlimTaskDto(
    val id: String,
    val title: String,
    val description: String,
    val taskStage: TaskStageDto,
    val startAt: Date,
    val endAt: Date,
    val points: Int?,
)

internal fun SlimTaskDto.toDomain() = SlimTaskInfo(
    id = id,
    title = title,
    description = description,
    taskStage = taskStage.toDomain(
        startAt = startAt,
        endAt = endAt,
        points = points,
    ),
    startAt = startAt,
    endAt = endAt,
    points = points
)