package ru.kyamshanov.mission.background_registration.api.ui.navigation

import ru.kyamshanov.mission.background_registration.api.domain.models.RegistrationField

interface BackgroundRegistrationLauncher {

    fun launch(requiredFields: List<RegistrationField>)
}