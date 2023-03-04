package ru.kyamshanov.mission.background_registration.impl.ui.screen

import androidx.compose.runtime.Composable
import ru.kyamshanov.mission.background_registration.impl.ui.composable.BackgroundRegistrationComposable
import ru.kyamshanov.mission.navigation_core.common.ComposableScreen
import ru.kyamshanov.mission.navigation_core.common.DestinationScreen

private const val DESTINATION_KEY = "BackgroundRegistrationScreen"

class BackgroundRegistrationScreen : DestinationScreen, ComposableScreen {

    override val composableSupplier: @Composable () -> Unit = { BackgroundRegistrationComposable() }
    override val destination: String
        get() = DESTINATION_KEY
}