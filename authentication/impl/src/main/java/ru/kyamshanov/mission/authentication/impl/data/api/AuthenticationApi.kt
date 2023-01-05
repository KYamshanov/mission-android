package ru.kyamshanov.mission.authentication.impl.data.api

import ru.kyamshanov.mission.authentication.impl.domain.AccessData

internal interface AuthenticationApi {

    suspend fun login(login: String, password: CharSequence): Result<AccessData>
}