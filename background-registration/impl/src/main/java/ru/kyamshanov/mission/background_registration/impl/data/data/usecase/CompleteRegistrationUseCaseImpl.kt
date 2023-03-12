package ru.kyamshanov.mission.background_registration.impl.data.data.usecase

import javax.inject.Inject
import ru.kyamshanov.mission.background_registration.impl.data.data.api.ProfileApi
import ru.kyamshanov.mission.background_registration.impl.data.data.model.BackRegisterRqDto
import ru.kyamshanov.mission.background_registration.impl.domain.model.RegistrationModel
import ru.kyamshanov.mission.background_registration.impl.domain.usecase.CompleteRegistrationUseCase
import ru.kyamshanov.mission.profile_facade.api.domain.usecase.GetProfileUseCase

internal class CompleteRegistrationUseCaseImpl @Inject constructor(
    private val profileApi: ProfileApi,
    private val getProfileUseCase: GetProfileUseCase,
) : CompleteRegistrationUseCase {

    override suspend fun perform(registrationModel: RegistrationModel): Result<Unit> = runCatching {
        profileApi.backRegister(
            body = BackRegisterRqDto(
                name = registrationModel.name,
                age = registrationModel.age
            )
        )
        getProfileUseCase.fetchProfile(refresh = true)
    }
}