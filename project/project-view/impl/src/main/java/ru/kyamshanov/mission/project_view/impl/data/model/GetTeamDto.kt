package ru.kyamshanov.mission.project_view.impl.data.model

internal data class GetTeamRsDto(
    val project: String,
    val participants: Collection<ParticipantDto>,
)