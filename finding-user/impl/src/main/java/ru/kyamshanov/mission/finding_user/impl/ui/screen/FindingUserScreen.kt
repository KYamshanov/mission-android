package ru.kyamshanov.mission.finding_user.impl.ui.screen

import androidx.compose.runtime.Composable
import ru.kyamshanov.mission.finding_user.impl.ui.composable.FindingUserComposable
import ru.kyamshanov.mission.navigation_core.common.ComposableScreen
import ru.kyamshanov.mission.navigation_core.common.DestinationScreen

private const val DESTINATION_KEY = "FindingUserScreen"

class FindingUserScreen : DestinationScreen, ComposableScreen {

    override val composableSupplier: @Composable () -> Unit = { FindingUserComposable() }
    override val destination: String
        get() = DESTINATION_KEY
}