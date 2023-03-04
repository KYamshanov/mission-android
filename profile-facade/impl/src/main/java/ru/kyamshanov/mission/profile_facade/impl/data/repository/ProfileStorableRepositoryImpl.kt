package ru.kyamshanov.mission.profile_facade.impl.data.repository

import javax.inject.Inject
import ru.kyamshanov.mission.profile_facade.impl.data.model.toDomain
import ru.kyamshanov.mission.profile_facade.impl.domain.model.ProfileInfoMap
import ru.kyamshanov.mission.profile_facade.impl.domain.repository.ProfileStorableRepository

internal class ProfileStorableRepositoryImpl @Inject constructor(
    private val profileApi: ru.kyamshanov.mission.profile_facade.impl.data.api.ProfileApi,
) : ProfileStorableRepository {

    private var savedProfile: ProfileInfoMap? = null

    override suspend fun fetchProfile(authUserId: String, refresh: Boolean): ProfileInfoMap =
        savedProfile?.takeIf { refresh.not() }
            ?: profileApi.fetch(authUserId)
                .getOrThrow()
                .toDomain()
                .also { savedProfile = it }
}