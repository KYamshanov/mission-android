package ru.kyamshanov.mission.project_view.impl.ui.screen

import androidx.compose.runtime.Composable
import ru.kyamshanov.mission.navigation_core.common.BoundaryDataComposableScreen
import ru.kyamshanov.mission.navigation_core.common.DestinationScreen
import ru.kyamshanov.mission.navigation_core.common.SerializableNavigationBoundaryData
import ru.kyamshanov.mission.project_view.impl.ui.composable.TotalPointsView
import ru.kyamshanov.mission.project_view.impl.ui.model.TotalPointsInfo

private const val DESTINATION_KEY = "TotalPointsViewScreen"
private const val POINTS_KEY = "points"

class TotalPointsViewScreen() : DestinationScreen, BoundaryDataComposableScreen {

    private var points: TotalPointsInfo? = null

    internal constructor(points: TotalPointsInfo) : this() {
        this.points = points
    }

    override val destination: String
        get() = DESTINATION_KEY

    override val boundaryData: SerializableNavigationBoundaryData
        get() = requireNotNull(points) { "Points cannot be null" }
    override val composableSupplier: @Composable (data: SerializableNavigationBoundaryData) -> Unit = { data ->
        TotalPointsView(requireNotNull(data as? TotalPointsInfo) { "$POINTS_KEY is required " })
    }
}