package ru.kyamshanov.mission.authentication.impl.ui.navigation

import ru.kyamshanov.mission.authentication.domain.AuthenticationLauncher
import ru.kyamshanov.mission.authentication.impl.ui.screen.AuthenticationScreen
import ru.kyamshanov.mission.navigation_core.api.Navigator
import javax.inject.Inject

class AuthenticationLauncherImpl @Inject constructor(
    private val navigator: Navigator
) : AuthenticationLauncher {

    override fun launch() {
        navigator.replaceTo(AuthenticationScreen())
    }
}