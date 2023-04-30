package ru.kyamshanov.mission.project_view.impl.data.model

internal data class AddParticipantRqDto(
    val projectId: String,
    val userId: String,
    val role : ParticipantDto.Role
)