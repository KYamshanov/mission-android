package ru.kyamshanov.mission.finding_user.impl.data.model

internal data class ParticipantDto(
    val userId: String,
    val userLogin: String,
    val role: Role,
) {

    enum class Role {
        PARTICIPANT,
        LEADER
    }
}