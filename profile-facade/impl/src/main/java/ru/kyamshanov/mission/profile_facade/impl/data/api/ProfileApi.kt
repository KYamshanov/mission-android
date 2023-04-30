package ru.kyamshanov.mission.profile_facade.impl.data.api

import ru.kyamshanov.mission.profile_facade.impl.data.model.FetchUserInfoRsDto

internal interface ProfileApi {

    suspend fun fetch(): FetchUserInfoRsDto
}