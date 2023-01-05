package ru.kyamshanov.mission.android

import ru.kyamshanov.mission.authentication.impl.ui.screen.AuthenticationScreen
import ru.kyamshanov.mission.navigation_core.api.Screen

internal class ComposableScreensProvider : ScreensProvider {

    override fun supply(): Collection<Screen> = listOf(
        AuthenticationScreen()
    )
}