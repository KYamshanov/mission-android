package ru.kyamshanov.mission.main_screen_feature.impl.ui.screen

import androidx.compose.runtime.Composable
import ru.kyamshanov.mission.main_screen_feature.impl.ui.composable.MainScreenComposable
import ru.kyamshanov.mission.navigation_core.common.ComposableScreen
import ru.kyamshanov.mission.navigation_core.common.DestinationScreen

private const val DESTINATION_KEY = "MainScreen"

class MainScreen : DestinationScreen, ComposableScreen {

    override val composableSupplier: @Composable () -> Unit = { MainScreenComposable() }
    override val destination: String
        get() = DESTINATION_KEY
}