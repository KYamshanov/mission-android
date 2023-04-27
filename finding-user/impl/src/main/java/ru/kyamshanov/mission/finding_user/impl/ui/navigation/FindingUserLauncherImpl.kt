package ru.kyamshanov.mission.finding_user.impl.ui.navigation

import javax.inject.Inject
import ru.kyamshanov.mission.finding_user.api.model.SearchStrategy
import ru.kyamshanov.mission.finding_user.api.navigation.FindingUserLauncher
import ru.kyamshanov.mission.finding_user.impl.domain.model.toInternal
import ru.kyamshanov.mission.finding_user.impl.ui.screen.FindingUserScreen
import ru.kyamshanov.mission.navigation_core.api.Navigator

class FindingUserLauncherImpl @Inject constructor(
    private val navigator: Navigator,
) : FindingUserLauncher {

    override fun launch() {
        navigator.navigateTo(FindingUserScreen())
    }

    override fun launch(searchStrategy: SearchStrategy) {
        navigator.navigateTo(FindingUserScreen(searchStrategy = searchStrategy.toInternal()))
    }
}