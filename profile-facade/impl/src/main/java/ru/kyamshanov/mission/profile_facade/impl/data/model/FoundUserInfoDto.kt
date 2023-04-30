package ru.kyamshanov.mission.profile_facade.impl.data.model

internal data class UserInfoDto(
    val id: String,
    val firstname: String?,
    val lastname: String?,
    val patronymic: String?,
    val group: String?
)