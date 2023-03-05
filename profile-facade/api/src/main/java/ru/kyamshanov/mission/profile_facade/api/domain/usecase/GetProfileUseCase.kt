package ru.kyamshanov.mission.profile_facade.api.domain.usecase

import ru.kyamshanov.mission.profile_facade.api.domain.model.ProfileInfo

interface GetProfileUseCase {

    suspend fun fetchProfile(authUserId: String, refresh: Boolean): Result<ProfileInfo>

    suspend fun getProfile(): Result<ProfileInfo>
}