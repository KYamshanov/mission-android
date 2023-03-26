package ru.kyamshanov.mission.creating_project.impl.ui.navigation

import javax.inject.Inject
import ru.kyamshanov.mission.creating_project.api.navigation.CreatingProjectLauncher
import ru.kyamshanov.mission.creating_project.impl.ui.screen.CreatingProjectScreen
import ru.kyamshanov.mission.navigation_core.api.Navigator

class CreatingProjectLauncherImpl @Inject constructor(
    private val navigator: Navigator,
) : CreatingProjectLauncher {

    override fun launch() {
        navigator.navigateTo(CreatingProjectScreen())
    }
}