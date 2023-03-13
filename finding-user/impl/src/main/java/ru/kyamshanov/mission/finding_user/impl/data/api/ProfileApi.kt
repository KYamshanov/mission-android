package ru.kyamshanov.mission.finding_user.impl.data.api

import ru.kyamshanov.mission.finding_user.impl.data.model.FindUsersRqDto
import ru.kyamshanov.mission.finding_user.impl.data.model.FindUsersRsDto

internal interface ProfileApi {

    suspend fun findUsers(body: FindUsersRqDto): FindUsersRsDto
}