package ru.kyamshanov.mission.android

import ru.kyamshanov.mission.navigation_core.api.Screen

internal interface ScreensProvider {

    fun supply(): Collection<Screen>
}