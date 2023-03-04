package ru.kyamshanov.mission.profile_facade.impl.data.api

import ru.kyamshanov.mission.profile_facade.impl.data.model.FetchUserDtoRs

internal interface ProfileApi {

    suspend fun fetch(authUserId : String): Result<FetchUserDtoRs>
}