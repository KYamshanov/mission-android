package ru.kyamshanov.mission.task.set_points.impl.ui.screen

import androidx.compose.runtime.Composable
import ru.kyamshanov.mission.navigation_core.api.NavigationBoundaryData
import ru.kyamshanov.mission.navigation_core.common.BoundaryDataComposableScreen
import ru.kyamshanov.mission.navigation_core.common.DestinationScreen
import ru.kyamshanov.mission.navigation_core.common.SerializableNavigationBoundaryData
import ru.kyamshanov.mission.project.common.domain.model.TaskId
import ru.kyamshanov.mission.task.set_points.impl.ui.composable.SetPointsComposable

class SetPointsScreen() : DestinationScreen, BoundaryDataComposableScreen {

    override val composableSupplier: @Composable (NavigationBoundaryData) -> Unit =
        { data ->
            val boundaryData = data as BoundaryData
            SetPointsComposable(
                taskId = TaskId(boundaryData.taskId),
                projectName = boundaryData.projectName,
                taskName = boundaryData.taskName,
                maxPoints = boundaryData.maxPoints
            )
        }

    private var _boundaryData: BoundaryData? = null
    override val boundaryData: SerializableNavigationBoundaryData
        get() = requireNotNull(_boundaryData)

    constructor(
        projectName: String,
        taskName: String,
        taskId: TaskId,
        maxPoints: Int,
    ) : this() {
        _boundaryData =
            BoundaryData(projectName = projectName, taskName = taskName, taskId = taskId.value, maxPoints = maxPoints)
    }

    private data class BoundaryData(
        val projectName: String,
        val taskName: String,
        val taskId: String,
        val maxPoints: Int,
    ) : SerializableNavigationBoundaryData
}