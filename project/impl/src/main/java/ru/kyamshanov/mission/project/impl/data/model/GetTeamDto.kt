package ru.kyamshanov.mission.project.impl.data.model

internal data class GetTeamRsDto(
    val project: String,
    val participants: Collection<ParticipantDto>,
)