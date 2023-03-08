package ru.kyamshanov.mission.background_registration.impl.data.data.api

internal interface ProfileApi {

    suspend fun backRegister(authUserId: String): Result<Unit>
}