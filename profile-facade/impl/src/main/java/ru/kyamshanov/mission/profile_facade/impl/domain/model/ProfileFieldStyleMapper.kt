package ru.kyamshanov.mission.profile_facade.impl.domain.model

import ru.kyamshanov.mission.background_registration.api.domain.models.RegistrationField

internal fun ProfileFieldStyle.toRegistrationField(): RegistrationField? = when (key) {
    PROFILE_AGE_KEY -> RegistrationField.AGE
    PROFILE_NAME_KEY -> RegistrationField.NAME
    else -> null
}