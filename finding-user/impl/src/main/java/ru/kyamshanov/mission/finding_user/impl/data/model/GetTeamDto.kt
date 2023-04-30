package ru.kyamshanov.mission.finding_user.impl.data.model

internal data class GetTeamRsDto(
    val project: String,
    val participants: Collection<ParticipantDto>,
)