package ru.kyamshanov.mission.background_registration.impl.ui.models

import ru.kyamshanov.mission.background_registration.api.domain.models.RegistrationField
import ru.kyamshanov.mission.navigation_core.common.SerializableNavigationBoundaryData

data class BackgroundRegistrationBoundaryData(
    val requiredFields: List<RegistrationField>,
) : SerializableNavigationBoundaryData