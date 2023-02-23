package ru.kyamshanov.mission.session_front.api.session

interface JwtLoggedSession : LoggedSession {

    val accessToken: String

    val refreshToken: String
}