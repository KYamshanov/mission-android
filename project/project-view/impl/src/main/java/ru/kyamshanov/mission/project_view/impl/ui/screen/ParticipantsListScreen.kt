package ru.kyamshanov.mission.project_view.impl.ui.screen

import androidx.compose.runtime.Composable
import ru.kyamshanov.mission.navigation_core.common.BoundaryDataComposableScreen
import ru.kyamshanov.mission.navigation_core.common.DestinationScreen
import ru.kyamshanov.mission.navigation_core.common.SerializableNavigationBoundaryData
import ru.kyamshanov.mission.project_view.impl.domain.model.ParticipantInfo
import ru.kyamshanov.mission.project_view.impl.ui.composable.ParticipantsListComposable
import ru.kyamshanov.mission.project_view.impl.ui.model.ProjectInfoSlim

class ParticipantsListScreen() : DestinationScreen, BoundaryDataComposableScreen {

    private var data: BoundaryData? = null

    internal constructor(
        projectInfoSlim: ProjectInfoSlim,
    ) : this() {
        data = BoundaryData(
            projectInfoSlim = projectInfoSlim,
        )
    }

    override val boundaryData: SerializableNavigationBoundaryData
        get() = requireNotNull(data)
    override val composableSupplier: @Composable (SerializableNavigationBoundaryData) -> Unit = { data ->
        val boundaryData = data as BoundaryData
        ParticipantsListComposable(
            projectInfoSlim = boundaryData.projectInfoSlim,
        )
    }

    private data class BoundaryData(
        val projectInfoSlim: ProjectInfoSlim,
    ) : SerializableNavigationBoundaryData
}