package ru.kyamshanov.mission.project_view.impl.domain.model

internal data class ProjectInfo(
    val id: String,
    val title: String,
    val description: String,
    val participants: List<ParticipantSlimInfo>,
    val tasks: List<SlimTaskInfo>,
    val projectStage: ProjectStage,
)
