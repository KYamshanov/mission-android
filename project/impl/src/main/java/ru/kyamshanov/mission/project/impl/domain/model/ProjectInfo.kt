package ru.kyamshanov.mission.project.impl.domain.model

internal data class ProjectInfo(
    val id: String,
    val title: String,
    val description: String,
    val participants: List<ParticipantFace>,
)
