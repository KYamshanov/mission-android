package ru.kyamshanov.mission.project_view.impl.data.model

import ru.kyamshanov.mission.project_view.impl.domain.model.SlimTaskInfo

internal data class SlimTaskDto(
    val id: String,
    val title: String,
    val description: String,
    val taskStage: TaskStageDto,
)

internal fun SlimTaskDto.toDomain() = SlimTaskInfo(
    id = id,
    title = title,
    description = description,
    taskStage = taskStage.toDomain()
)