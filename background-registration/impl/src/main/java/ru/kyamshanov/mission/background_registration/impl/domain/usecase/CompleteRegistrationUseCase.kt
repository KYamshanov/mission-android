package ru.kyamshanov.mission.background_registration.impl.domain.usecase

import ru.kyamshanov.mission.background_registration.impl.domain.model.RegistrationModel

internal interface CompleteRegistrationUseCase {

    suspend fun perform(registrationModel: RegistrationModel): Result<Unit>
}