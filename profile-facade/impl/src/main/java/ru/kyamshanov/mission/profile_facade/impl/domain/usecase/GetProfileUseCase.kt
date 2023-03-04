package ru.kyamshanov.mission.profile_facade.impl.domain.usecase

import javax.inject.Inject
import ru.kyamshanov.mission.profile_facade.api.domain.model.ProfileInfo
import ru.kyamshanov.mission.profile_facade.api.domain.usecase.GetProfileUseCase
import ru.kyamshanov.mission.profile_facade.impl.domain.model.toProfileInfo

internal class GetProfileUseCaseImpl @Inject constructor(
    private val profileStorableRepository: ru.kyamshanov.mission.profile_facade.impl.domain.repository.ProfileStorableRepository,
) : GetProfileUseCase {

    override suspend fun getProfile(authUserId: String): Result<ProfileInfo> = runCatching {
        profileStorableRepository.fetchProfile(authUserId, refresh = false)
            .toProfileInfo()
    }
}