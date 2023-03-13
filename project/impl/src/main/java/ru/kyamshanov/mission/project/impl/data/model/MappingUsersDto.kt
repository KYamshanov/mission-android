package ru.kyamshanov.mission.project.impl.data.model

import ru.kyamshanov.mission.project.impl.domain.model.ParticipantFace

internal typealias MappingRqDto = List<String>

internal data class MappingRsDto(
    val users: List<UserInfo>,
) {

    data class UserInfo(
        val id: String,
        val name: String?,
        val age: Int?,
    )
}

internal fun MappingRsDto.toDomain(): List<ParticipantFace> =
    users.map { userInfo ->
        ParticipantFace(
            userId = userInfo.id, name = userInfo.name, age = userInfo.age
        )
    }