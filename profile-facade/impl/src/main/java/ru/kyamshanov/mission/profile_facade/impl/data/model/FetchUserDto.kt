package ru.kyamshanov.mission.profile_facade.impl.data.model

import ru.kyamshanov.mission.profile_facade.impl.domain.model.ProfileInfoMap

internal data class FetchUserDtoRs(
    val userId: String,
    val login: String,
    val profile: Map<String, Any>,
)

internal fun FetchUserDtoRs.toDomain() = ProfileInfoMap(
    userId = userId,
    info = profile
)