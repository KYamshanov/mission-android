package ru.kyamshanov.mission.background_registration.impl.domain.model

internal data class RegistrationModel(
    val firstname: String?,
    val lastname: String?,
    val patronymic: String?,
    val group: String?
)