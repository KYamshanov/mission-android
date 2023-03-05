package ru.kyamshanov.mission.profile_facade.impl.domain.usecase

import javax.inject.Inject
import ru.kyamshanov.mission.profile_facade.api.domain.model.ProfileInfo
import ru.kyamshanov.mission.profile_facade.api.domain.usecase.GetProfileUseCase
import ru.kyamshanov.mission.profile_facade.impl.domain.model.toProfileInfo
import ru.kyamshanov.mission.profile_facade.impl.domain.repository.ProfileStorableRepository

internal class GetProfileUseCaseImpl @Inject constructor(
    private val profileStorableRepository: ProfileStorableRepository,
) : GetProfileUseCase {

    override suspend fun fetchProfile(authUserId: String, refresh: Boolean): Result<ProfileInfo> = runCatching {
        profileStorableRepository.fetchProfile(authUserId, refresh = refresh)
            .toProfileInfo()
    }

    override suspend fun getProfile(): Result<ProfileInfo> = runCatching {
        profileStorableRepository.getProfile()
            .toProfileInfo()
    }
}