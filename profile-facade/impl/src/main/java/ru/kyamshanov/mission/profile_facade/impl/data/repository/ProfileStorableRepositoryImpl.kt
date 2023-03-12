package ru.kyamshanov.mission.profile_facade.impl.data.repository

import javax.inject.Inject
import ru.kyamshanov.mission.profile_facade.impl.data.api.ProfileApi
import ru.kyamshanov.mission.profile_facade.impl.data.model.toDomain
import ru.kyamshanov.mission.profile_facade.impl.domain.model.ProfileInfoMap
import ru.kyamshanov.mission.profile_facade.impl.domain.repository.ProfileStorableRepository

internal class ProfileStorableRepositoryImpl @Inject constructor(
    private val profileApi: ProfileApi,
) : ProfileStorableRepository {

    private var savedProfile: ProfileInfoMap? = null

    override suspend fun fetchProfile(refresh: Boolean): ProfileInfoMap =
        savedProfile?.takeIf { refresh.not() }
            ?: profileApi.fetch().toDomain().also { savedProfile = it }
}