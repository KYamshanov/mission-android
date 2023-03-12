package ru.kyamshanov.mission.profile_facade.api.domain.interactor

interface VerifyingProfileInteractor {

    suspend fun completeProfile(fetchProfile: Boolean = false)
}