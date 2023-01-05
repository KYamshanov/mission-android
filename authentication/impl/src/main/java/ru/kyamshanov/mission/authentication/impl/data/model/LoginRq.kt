package ru.kyamshanov.mission.authentication.impl.data.model

internal data class LoginRq(
    val login: String,
    val password: String,
    val info: Map<String, Any>? = null
)