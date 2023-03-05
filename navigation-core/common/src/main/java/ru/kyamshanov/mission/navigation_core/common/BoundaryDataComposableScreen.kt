package ru.kyamshanov.mission.navigation_core.common

import androidx.compose.runtime.Composable
import ru.kyamshanov.mission.navigation_core.api.Screen

const val BOUNDARY_DATA_HOLDER_KEY = "BoundaryData"

interface BoundaryDataComposableScreen : Screen {

    val boundaryData: SerializableNavigationBoundaryData

    val composableSupplier: @Composable (data: SerializableNavigationBoundaryData) -> Unit
}