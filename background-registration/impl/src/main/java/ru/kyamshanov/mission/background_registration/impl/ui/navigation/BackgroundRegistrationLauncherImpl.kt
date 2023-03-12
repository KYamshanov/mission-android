package ru.kyamshanov.mission.background_registration.impl.ui.navigation

import javax.inject.Inject
import ru.kyamshanov.mission.background_registration.api.domain.models.RegistrationField
import ru.kyamshanov.mission.background_registration.api.ui.navigation.BackgroundRegistrationLauncher
import ru.kyamshanov.mission.background_registration.impl.ui.models.BackgroundRegistrationBoundaryData
import ru.kyamshanov.mission.background_registration.impl.ui.screen.BackgroundRegistrationScreen
import ru.kyamshanov.mission.navigation_core.api.Navigator

class BackgroundRegistrationLauncherImpl @Inject constructor(
    private val navigator: Navigator,
) : BackgroundRegistrationLauncher {

    override fun launch(requiredFields: List<RegistrationField>) {
        navigator.navigateTo(BackgroundRegistrationScreen(BackgroundRegistrationBoundaryData(requiredFields)))
    }
}