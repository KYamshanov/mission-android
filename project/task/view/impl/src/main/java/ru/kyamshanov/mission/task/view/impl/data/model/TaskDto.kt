package ru.kyamshanov.mission.task.view.impl.data.model

import java.util.Date

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
    val availableAddSubtask: Boolean,
)
