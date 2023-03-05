package ru.kyamshanov.mission.background_registration.impl.ui.screen

import androidx.compose.runtime.Composable
import ru.kyamshanov.mission.background_registration.impl.ui.composable.BackgroundRegistrationComposable
import ru.kyamshanov.mission.background_registration.impl.ui.models.BackgroundRegistrationBoundaryData
import ru.kyamshanov.mission.navigation_core.common.BoundaryDataComposableScreen
import ru.kyamshanov.mission.navigation_core.common.DestinationScreen
import ru.kyamshanov.mission.navigation_core.common.SerializableNavigationBoundaryData

private const val DESTINATION_KEY = "BackgroundRegistrationScreen"

class BackgroundRegistrationScreen(
    private val nullableBoundaryData: BackgroundRegistrationBoundaryData? = null,
) : DestinationScreen, BoundaryDataComposableScreen {

    override val boundaryData: BackgroundRegistrationBoundaryData
        get() = requireNotNull(nullableBoundaryData) { "Boundary data cannot be nul for open screen. Please pass data through constructor" }

    override val composableSupplier: @Composable (SerializableNavigationBoundaryData) -> Unit =
        { rawData -> BackgroundRegistrationComposable(rawData as BackgroundRegistrationBoundaryData) }

    override val destination: String
        get() = DESTINATION_KEY
}