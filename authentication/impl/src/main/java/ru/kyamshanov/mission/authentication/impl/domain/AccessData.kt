package ru.kyamshanov.mission.authentication.impl.domain

internal data class AccessData(
    val accessToken: String,
    val refreshToken: String
)