package ru.kyamshanov.mission.finding_user.impl.ui.screen

import androidx.compose.runtime.Composable
import ru.kyamshanov.mission.finding_user.impl.domain.model.InternalSearchStrategy
import ru.kyamshanov.mission.finding_user.impl.ui.composable.FindingUserComposable
import ru.kyamshanov.mission.navigation_core.common.BoundaryDataComposableScreen
import ru.kyamshanov.mission.navigation_core.common.DestinationScreen
import ru.kyamshanov.mission.navigation_core.common.SerializableNavigationBoundaryData

private const val DESTINATION_KEY = "FindingUserScreen"

class FindingUserScreen() : DestinationScreen, BoundaryDataComposableScreen {

    private var searchStrategy: InternalSearchStrategy = InternalSearchStrategy.All

    internal constructor(searchStrategy: InternalSearchStrategy) : this() {
        this.searchStrategy = searchStrategy
    }

    override val boundaryData: SerializableNavigationBoundaryData
        get() = searchStrategy

    override val composableSupplier: @Composable (SerializableNavigationBoundaryData) -> Unit =
        { data: SerializableNavigationBoundaryData ->
            FindingUserComposable(data as InternalSearchStrategy)
        }
    override val destination: String
        get() = DESTINATION_KEY
}