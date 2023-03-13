package ru.kyamshanov.mission.profile_facade.impl.domain.interactor

import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.withContext
import ru.kyamshanov.mission.background_registration.api.ui.navigation.BackgroundRegistrationLauncher
import ru.kyamshanov.mission.profile_facade.api.domain.interactor.VerifyingProfileInteractor
import ru.kyamshanov.mission.profile_facade.impl.domain.model.toRegistrationField
import ru.kyamshanov.mission.profile_facade.impl.domain.repository.ProfileStorableRepository
import ru.kyamshanov.mission.profile_facade.impl.domain.usecase.VerifyProfileCompletedUseCase

internal class VerifyingProfileInteractorImpl @Inject constructor(
    private val profileStorableRepository: ProfileStorableRepository,
    private val verifyProfileCompletedUseCase: VerifyProfileCompletedUseCase,
    private val backgroundRegistrationLauncher: dagger.Lazy<BackgroundRegistrationLauncher>,
) : VerifyingProfileInteractor {

    override suspend fun completeProfile(fetchProfile: Boolean) {
        runCatching {
            val profile = profileStorableRepository.fetchProfile(refresh = fetchProfile)
            val requiredFields = verifyProfileCompletedUseCase.verify(profile)
            if (requiredFields.isNotEmpty()) withContext(Dispatchers.Main + NonCancellable) {
                backgroundRegistrationLauncher.get()
                    .launch(requiredFields.mapNotNull { it.toRegistrationField() })
            }
        }
    }
}