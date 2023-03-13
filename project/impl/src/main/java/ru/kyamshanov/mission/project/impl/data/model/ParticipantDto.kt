package ru.kyamshanov.mission.project.impl.data.model

internal data class ParticipantDto(
    val userId: String,
    val role: Role,
) {

    enum class Role {
        PARTICIPANT,
        LEADER
    }
}