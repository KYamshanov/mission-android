package ru.kyamshanov.mission.finding_user.impl.data.repository

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.kyamshanov.mission.core.common.Converter
import ru.kyamshanov.mission.finding_user.impl.data.api.ProfileApi
import ru.kyamshanov.mission.finding_user.impl.data.api.ProjectApi
import ru.kyamshanov.mission.finding_user.impl.data.model.FindUsersRqDto
import ru.kyamshanov.mission.finding_user.impl.data.model.FindUsersRsDto
import ru.kyamshanov.mission.finding_user.impl.data.model.ParticipantDto
import ru.kyamshanov.mission.finding_user.impl.domain.model.UserInfo
import ru.kyamshanov.mission.finding_user.impl.domain.repository.UserRepository
import ru.kyamshanov.mission.session_front.api.SessionInfo
import ru.kyamshanov.mission.session_front.api.model.UserRole

internal class UserRepositoryImpl @Inject constructor(
    private val profileApi: ProfileApi,
    private val projectApi: ProjectApi,
    private val sessionInfo: SessionInfo,
    private val userInfoConverter: Converter<FindUsersRsDto.FoundUserInfo, UserInfo>,
    private val participantConverter : Converter<ParticipantDto, UserInfo>
) : UserRepository {

    override fun findByName(name: String): Flow<UserInfo> = flow {
        profileApi.findUsers(FindUsersRqDto(name = name, age = null))
            .users.map { userInfoConverter(it) }
            .forEach { emit(it) }
    }

    override fun findInProjectTeamByName(projectId: String, name: String): Flow<UserInfo> = flow {
        when {
            sessionInfo.hasRole(UserRole.MANAGER) -> projectApi.getManagedTeam(projectId = projectId)
            else -> projectApi.getTeam(projectId = projectId)
        }.participants
            .run { if (name.isNotBlank()) filter { it.userLogin.contains(name) } else this }
            .map { participantConverter(it) }
            .forEach { emit(it) }
    }
}