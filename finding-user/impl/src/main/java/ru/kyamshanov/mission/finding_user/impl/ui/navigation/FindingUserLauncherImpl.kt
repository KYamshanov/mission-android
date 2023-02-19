package ru.kyamshanov.mission.finding_user.impl.ui.navigation

import ru.kyamshanov.mission.finding_user.api.navigation.FindingUserLauncher
import ru.kyamshanov.mission.navigation_core.api.Navigator
import ru.kyamshanov.mission.finding_user.impl.ui.screen.FindingUserScreen
import javax.inject.Inject

class FindingUserLauncherImpl @Inject constructor(
    private val navigator: Navigator
) : FindingUserLauncher {

    override fun launch() {
        navigator.navigateTo(FindingUserScreen())
    }
}