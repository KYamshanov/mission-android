package ru.kyamshanov.mission.profile.impl.ui.screen

import androidx.compose.runtime.Composable
import ru.kyamshanov.mission.navigation_core.common.ComposableScreen
import ru.kyamshanov.mission.navigation_core.common.DestinationScreen
import ru.kyamshanov.mission.profile.impl.ui.composable.ProfileComposable

private const val DESTINATION_KEY = "ProfileScreen"

class ProfileScreen : DestinationScreen, ComposableScreen {

    override val composableSupplier: @Composable () -> Unit = { ProfileComposable() }
    override val destination: String
        get() = DESTINATION_KEY
}