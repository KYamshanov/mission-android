package ru.kyamshanov.mission.task.view.impl.data.model

import java.util.Date

internal data class EditSubtaskRqDto(
    val subtaskId: String,
    val title: String?,
    val description: String?,
    val responsible: String?,
    val state: SubTaskStageDto?,
    val startAt: Date?,
    val endAt: Date?,
    val executionResult: String?,
)


