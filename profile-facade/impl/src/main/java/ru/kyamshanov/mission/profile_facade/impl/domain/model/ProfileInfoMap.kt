package ru.kyamshanov.mission.profile_facade.impl.domain.model

internal data class ProfileInfoMap(
    val userId: String,
    val info: Map<String, Any?>,
)
