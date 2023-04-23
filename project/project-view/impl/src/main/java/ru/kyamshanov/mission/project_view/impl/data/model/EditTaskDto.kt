package ru.kyamshanov.mission.project_view.impl.data.model

import java.time.LocalDateTime

internal typealias EditTaskSetRqDto = List<EditTaskRqDto>

internal data class EditTaskRqDto(
    val taskId: String,
    val title: String? = null,
    val description: String? = null,
    val startAt: LocalDateTime? = null,
    val endAt: LocalDateTime? = null,
    val maxPoints: Int? = null,
    val points: Int? = null,
)


