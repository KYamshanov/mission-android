package ru.kyamshanov.mission.authentication.impl.ui.screen

import androidx.compose.runtime.Composable
import ru.kyamshanov.mission.authentication.impl.ui.composable.AuthenticationComposable
import ru.kyamshanov.mission.navigation_core.common.ComposableScreen
import ru.kyamshanov.mission.navigation_core.common.DestinationScreen

private const val DESTINATION_KEY = "AuthenticationScreen"

class AuthenticationScreen() : DestinationScreen, ComposableScreen {

    override val composableSupplier: @Composable () -> Unit = { AuthenticationComposable() }
    override val destination: String
        get() = DESTINATION_KEY
}