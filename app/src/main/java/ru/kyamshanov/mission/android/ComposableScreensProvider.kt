package ru.kyamshanov.mission.android

import ru.kyamshanov.mission.authentication.impl.ui.screen.AuthenticationScreen
import ru.kyamshanov.mission.main_screen_feature.impl.ui.screen.MainScreen
import ru.kyamshanov.mission.navigation_core.api.Screen
import ru.kyamshanov.mission.profile.impl.ui.screen.ProfileScreen

internal class ComposableScreensProvider : ScreensProvider {

    override fun supply(): Collection<Screen> = listOf(
        AuthenticationScreen(),
        MainScreen(),
        ProfileScreen()
    )
}