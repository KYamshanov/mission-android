package ru.kyamshanov.mission.task.view.impl.data.model

import java.time.LocalDateTime
import java.util.Date

internal data class EditTaskRqDto(
    val taskId: String,
    val title: String?,
    val description: String?,
    val startAt: Date?,
    val endAt: Date?,
    val maxPoints: Int?,
    val points: Int?
)


