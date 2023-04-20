package ru.kyamshanov.mission.task.view.impl.data.model

import java.util.Date

internal data class CreateSubTaskRqDto(
    val taskId: String,
    val title: String,
    val description: String,
    val startAt: Date,
    val endAt: Date,
    val responsible: String,
)

internal data class CreateSubTaskRsDto(
    val id: String
)