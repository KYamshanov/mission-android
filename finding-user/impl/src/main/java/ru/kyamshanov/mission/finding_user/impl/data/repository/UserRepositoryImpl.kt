package ru.kyamshanov.mission.finding_user.impl.data.repository

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.kyamshanov.mission.finding_user.impl.data.api.ProfileApi
import ru.kyamshanov.mission.finding_user.impl.data.api.ProjectApi
import ru.kyamshanov.mission.finding_user.impl.data.model.FindUsersRqDto
import ru.kyamshanov.mission.finding_user.impl.data.model.ParticipantDto
import ru.kyamshanov.mission.finding_user.impl.data.model.toDomain
import ru.kyamshanov.mission.finding_user.impl.domain.model.UserInfo
import ru.kyamshanov.mission.finding_user.impl.domain.repository.UserRepository

internal class UserRepositoryImpl @Inject constructor(
    private val profileApi: ProfileApi,
    private val projectApi: ProjectApi,
) : UserRepository {

    override fun findByName(name: String): Flow<UserInfo> = flow {
        profileApi.findUsers(FindUsersRqDto(name = name, age = null))
            .toDomain()
            .forEach { emit(it) }
    }

    override fun findInProjectTeamByName(projectId: String, name: String): Flow<UserInfo> = flow {
        projectApi.getTeam(projectId)
            .participants
            .filter { it.userLogin.contains(name) }
            .map { it.toUserInfo() }
            .forEach { emit(it) }
    }

    private fun ParticipantDto.toUserInfo() = UserInfo(
        id = userId,
        name = userLogin,
        age = null
    )
}