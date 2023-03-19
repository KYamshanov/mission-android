package ru.kyamshanov.mission.project.impl.data.model

import ru.kyamshanov.mission.project.impl.domain.model.ParticipantSlimInfo

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

internal fun ParticipantDto.toDomain(): ParticipantSlimInfo =
    ParticipantSlimInfo(
        userId = userId,
        name = userLogin,
        age = null
    )