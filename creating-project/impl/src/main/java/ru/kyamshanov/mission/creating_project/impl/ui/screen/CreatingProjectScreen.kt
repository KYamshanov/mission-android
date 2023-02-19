package ru.kyamshanov.mission.creating_project.impl.ui.screen

import androidx.compose.runtime.Composable
import ru.kyamshanov.mission.creating_project.impl.ui.composable.CreatingProjectComposable
import ru.kyamshanov.mission.navigation_core.common.ComposableScreen
import ru.kyamshanov.mission.navigation_core.common.DestinationScreen

private const val DESTINATION_KEY = "CreatingProjectScreen"

class CreatingProjectScreen : DestinationScreen, ComposableScreen {

    override val composableSupplier: @Composable () -> Unit = { CreatingProjectComposable() }
    override val destination: String
        get() = DESTINATION_KEY
}