package ru.kyamshanov.mission.project.impl.data.model

internal data class FindProjectRsDto(
    val id: String,
    val title: String,
    val description: String,
    val participants: List<ParticipantDto>,
)