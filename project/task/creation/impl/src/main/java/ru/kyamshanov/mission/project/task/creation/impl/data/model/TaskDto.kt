package ru.kyamshanov.mission.project.task.creation.impl.data.model

import java.util.Date

internal data class CreateTaskRqDto(
    val projectId: String,
    val title: String,
    val text: String,
    val startAt: Date,
    val endAt: Date,
    val maxPoints: Int,
)

internal data class CreateTaskRsDto(
    val taskId: String,
)

internal data class GetTaskRsDto(
    val title: String,
    val text: String,
    val createdAt: Date,
    val taskStage: TaskStageDto,
    val startAt: Date,
    val endAt: Date,
    val maxPoints: Int,
    val points: Int,
)

internal data class GetTasksRsDto(
    val tasks: Collection<String>,
)