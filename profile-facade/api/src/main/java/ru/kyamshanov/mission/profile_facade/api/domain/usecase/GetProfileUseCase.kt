package ru.kyamshanov.mission.profile_facade.api.domain.usecase

import ru.kyamshanov.mission.profile_facade.api.domain.model.ProfileInfo

interface GetProfileUseCase {

    suspend fun fetchProfile(refresh: Boolean = false): Result<ProfileInfo>
}