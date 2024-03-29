package ru.kyamshanov.mission.project_view.impl.data.model

import ru.kyamshanov.mission.project_view.impl.domain.model.ParticipantInfo

internal data class ParticipantDto(
    val userId: String,
    val userLogin: String,
    val role: Role,
) {

    enum class Role {
        PARTICIPANT,
        LEADER,
        MENTOR,
    }
}

internal fun ParticipantDto.toDomain(): ParticipantInfo =
    ParticipantInfo(
        userId = userId,
        role = role.toDomain(),
        name = userLogin,
        age = null
    )

internal fun ParticipantDto.Role.toDomain(): ParticipantInfo.Role = when (this) {
    ParticipantDto.Role.PARTICIPANT -> ParticipantInfo.Role.PARTICIPANT
    ParticipantDto.Role.LEADER -> ParticipantInfo.Role.LEADER
    ParticipantDto.Role.MENTOR -> ParticipantInfo.Role.MENTOR
}

internal fun ParticipantInfo.Role.toDto(): ParticipantDto.Role = when (this) {
    ParticipantInfo.Role.PARTICIPANT -> ParticipantDto.Role.PARTICIPANT
    ParticipantInfo.Role.LEADER -> ParticipantDto.Role.LEADER
    ParticipantInfo.Role.MENTOR -> ParticipantDto.Role.MENTOR
}