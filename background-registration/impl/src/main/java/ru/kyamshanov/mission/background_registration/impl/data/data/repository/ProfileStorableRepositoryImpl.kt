package ru.kyamshanov.mission.profile_facade.impl.data.repository

import javax.inject.Inject
import ru.kyamshanov.mission.background_registration.impl.data.data.api.ProfileApi
import ru.kyamshanov.mission.profile_facade.impl.data.model.toDomain
import ru.kyamshanov.mission.profile_facade.impl.domain.model.ProfileInfoMap
import ru.kyamshanov.mission.profile_facade.impl.domain.repository.ProfileStorableRepository

internal class ProfileStorableRepositoryImpl @Inject constructor(
    private val profileApi: ProfileApi,
) : ProfileStorableRepository {

    private var savedProfile: Pair<String, ProfileInfoMap>? = null

    override suspend fun fetchProfile(authUserId: String, refresh: Boolean): ProfileInfoMap =
        savedProfile?.takeIf { refresh.not() && it.first == authUserId }?.second
            ?: run {
                profileApi.backRegister(authUserId)
                    .getOrThrow()
                    .toDomain()
            }
                .also { savedProfile = Pair(authUserId, it) }

    override fun getProfile(): ProfileInfoMap = savedProfile?.second
        ?: throw IllegalStateException("The profile has not been initialized yet. Please, use fetchProfile")
}