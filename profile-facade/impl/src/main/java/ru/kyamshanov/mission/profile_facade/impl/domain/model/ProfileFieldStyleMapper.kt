package ru.kyamshanov.mission.profile_facade.impl.domain.model

import ru.kyamshanov.mission.background_registration.api.domain.models.RegistrationField

internal fun ProfileFieldStyle.toRegistrationField(): RegistrationField? = when (key) {
    PROFILE_FIRSTNAME -> RegistrationField.FIRSTNAME
    PROFILE_LASTNAME -> RegistrationField.LASTNMAE
    PROFILE_PATRONYMIC -> RegistrationField.PATRONYMIC
    PROFILE_GROUP -> RegistrationField.GROUP
    else -> null
}