package ru.kyamshanov.mission.task.view.impl.ui.screen

import androidx.compose.runtime.Composable
import ru.kyamshanov.mission.navigation_core.common.BoundaryDataComposableScreen
import ru.kyamshanov.mission.navigation_core.common.DestinationScreen
import ru.kyamshanov.mission.navigation_core.common.SerializableNavigationBoundaryData
import ru.kyamshanov.mission.task.view.impl.domain.model.ProjectInfo
import ru.kyamshanov.mission.task.view.impl.ui.composable.SubtaskCreationComposable

private const val DESTINATION_KEY = "SubtaskCreationScreen"

class SubtaskCreationScreen() : DestinationScreen, BoundaryDataComposableScreen {

    override val destination: String
        get() = DESTINATION_KEY

    private var data: BoundaryData? = null

    internal constructor(
        taskId: String,
        projectInfo: ProjectInfo,
    ) : this() {
        data = BoundaryData(
            taskId = taskId,
            projectInfoBoundary = projectInfo,
        )
    }

    override val boundaryData: SerializableNavigationBoundaryData
        get() = requireNotNull(data)
    override val composableSupplier: @Composable (SerializableNavigationBoundaryData) -> Unit = { data ->
        val boundaryData = data as BoundaryData
        SubtaskCreationComposable(
            taskId = boundaryData.taskId,
            projectInfo = boundaryData.projectInfoBoundary
        )
    }

    private data class BoundaryData(
        val taskId: String,
        val projectInfoBoundary: ProjectInfo,
    ) : SerializableNavigationBoundaryData
}