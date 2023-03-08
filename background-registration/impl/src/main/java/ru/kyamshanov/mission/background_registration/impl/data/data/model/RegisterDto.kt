package ru.kyamshanov.mission.background_registration.impl.data.data.model

internal data class BackRegisterRqDto(
    val userId: String,
    val info: Info
) {
    data class Info(
        val name: String?,
        val age: String?
    )
}