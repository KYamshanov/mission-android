package ru.kyamshanov.mission.finding_user.impl.data.repository

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.kyamshanov.mission.finding_user.impl.data.api.ProfileApi
import ru.kyamshanov.mission.finding_user.impl.data.model.FindUsersRqDto
import ru.kyamshanov.mission.finding_user.impl.data.model.toDomain
import ru.kyamshanov.mission.finding_user.impl.domain.model.UserInfo
import ru.kyamshanov.mission.finding_user.impl.domain.repository.UserRepository

internal class UserRepositoryImpl @Inject constructor(
    private val profileApi: ProfileApi,
) : UserRepository {

    override fun findByName(name: String): Flow<UserInfo> = flow {
        profileApi.findUsers(FindUsersRqDto(name = name, age = null))
            .toDomain()
            .forEach { emit(it) }
    }
}