package ru.kyamshanov.mission.project_view.impl.data.model

import java.util.Date

internal data class ProjectInfoDto(
    val id: String,
    val title: String,
    val description: String,
    val currentTask: GetTaskRsDto?,
    val startAt: Date?,
    val endAt: Date?,
    val tasks: List<SlimTaskDto>,
    val stage: ProjectStageDto,
)