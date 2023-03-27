package ru.kyamshanov.mission.project_view.impl.domain.model

internal data class SlimTaskInfo(
    val id: String,
    val title: String,
    val description: String,
    val taskStage: TaskStage,
)