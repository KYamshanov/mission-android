package ru.kyamshanov.mission.project_view.impl.domain.model

import java.util.Date

internal data class TaskInfo(
    val id: String,
    val title: String,
    val description: String,
    val taskStage: TaskStage,
    val startAt: Date,
    val endAt: Date,
    val points: Int?,
    val createdAt: Date,
    val maxPoints: Int,
)