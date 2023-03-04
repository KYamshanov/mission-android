package ru.kyamshanov.mission.profile_facade.api.domain.usecase

import ru.kyamshanov.mission.profile_facade.api.domain.model.ProfileInfo

interface GetProfileUseCase {

    suspend fun getProfile(authUserId: String): Result<ProfileInfo>
}