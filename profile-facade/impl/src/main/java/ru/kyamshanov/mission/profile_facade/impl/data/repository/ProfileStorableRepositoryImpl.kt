package ru.kyamshanov.mission.profile_facade.impl.data.repository

import javax.inject.Inject
import ru.kyamshanov.mission.profile_facade.impl.data.api.ProfileApi
import ru.kyamshanov.mission.profile_facade.impl.data.model.FetchUserInfoRsDto
import ru.kyamshanov.mission.profile_facade.impl.data.model.UserInfoFieldDto
import ru.kyamshanov.mission.profile_facade.impl.domain.model.PROFILE_FIRSTNAME
import ru.kyamshanov.mission.profile_facade.impl.domain.model.PROFILE_GROUP
import ru.kyamshanov.mission.profile_facade.impl.domain.model.PROFILE_LASTNAME
import ru.kyamshanov.mission.profile_facade.impl.domain.model.PROFILE_PATRONYMIC
import ru.kyamshanov.mission.profile_facade.impl.domain.model.ProfileFieldStyle
import ru.kyamshanov.mission.profile_facade.impl.domain.model.ProfileInfoMap
import ru.kyamshanov.mission.profile_facade.impl.domain.repository.ProfileStorableRepository

internal class ProfileStorableRepositoryImpl @Inject constructor(
    private val profileApi: ProfileApi,
) : ProfileStorableRepository {

    override var savedProfile: ProfileInfoMap? = null
        private set

    override suspend fun fetchProfile(refresh: Boolean): ProfileInfoMap =
        savedProfile?.takeIf { refresh.not() }
            ?: profileApi.fetch().toMap().also { savedProfile = it }

    private fun FetchUserInfoRsDto.toMap() = ProfileInfoMap(
        userId = userInfo.id,
        info = buildMap {
            userInfo.firstname?.let { put(PROFILE_FIRSTNAME, it) }
            userInfo.lastname?.let { put(PROFILE_LASTNAME, it) }
            userInfo.patronymic?.let { put(PROFILE_PATRONYMIC, it) }
            userInfo.group?.let { put(PROFILE_GROUP, it) }
        },
        requiredFields = requiredField.map { it.toDomain() }
    )

    private fun UserInfoFieldDto.toDomain() = when (this) {
        UserInfoFieldDto.FIRSTNAME -> ProfileFieldStyle(PROFILE_FIRSTNAME, String::class)
        UserInfoFieldDto.LASTNAME -> ProfileFieldStyle(PROFILE_LASTNAME, String::class)
        UserInfoFieldDto.PATRONYMIC -> ProfileFieldStyle(PROFILE_PATRONYMIC, String::class)
        UserInfoFieldDto.GROUP -> ProfileFieldStyle(PROFILE_GROUP, String::class)
    }
}