package ru.kyamshanov.mission.finding_user.impl.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.kyamshanov.mission.finding_user.impl.domain.model.UserInfo

internal interface UserRepository {

    fun findByName(firstName: String, secondName: String): Flow<UserInfo>
}