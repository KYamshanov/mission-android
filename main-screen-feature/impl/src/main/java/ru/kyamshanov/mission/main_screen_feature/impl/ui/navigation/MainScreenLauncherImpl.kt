package ru.kyamshanov.mission.main_screen_feature.impl.ui.navigation

import ru.kyamshanov.mission.main_screen_feature.api.navigation.MainScreenLauncher
import ru.kyamshanov.mission.main_screen_feature.impl.ui.screen.MainScreen
import ru.kyamshanov.mission.navigation_core.api.Navigator
import javax.inject.Inject

class MainScreenLauncherImpl @Inject constructor(
    private val navigator: Navigator
) : MainScreenLauncher {

    override fun launch() {
        println("Open MainScreen")
        navigator.replaceTo(MainScreen())
    }
}