package ru.kyamshanov.mission.task.view.impl.data.model

import java.util.Date

internal data class SubtaskDto(
    val taskId: String,
    val title: String,
    val description: String,
    val createAt: Date,
    val startAt: Date,
    val endAt: Date,
    val responsible: UserInfoDto,
    val stage: SubTaskStageDto,
    val executionResult: String?,
    val id: String,
)

enum class SubTaskStageDto {
    CREATED,
    IN_WORK,
    FINISHED
}