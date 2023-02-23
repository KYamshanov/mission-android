package ru.kyamshanov.mission.profile.impl.ui.navigation

import ru.kyamshanov.mission.navigation_core.api.Navigator
import ru.kyamshanov.mission.profile.api.navigation.ProfileLauncher
import ru.kyamshanov.mission.profile.impl.ui.screen.ProfileScreen
import javax.inject.Inject

class ProfileLauncherImpl @Inject constructor(
    private val navigator: Navigator
) : ProfileLauncher {

    override fun launch() {
        navigator.navigateTo(ProfileScreen())
    }
}