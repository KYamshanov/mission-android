package ru.kyamshanov.mission.profile_facade.api.domain.model

data class ProfileInfo(
    val userId: String,
    val firstname: String?,
    val lastname: String?,
    val patronymic: String?,
    val group: String?
)
