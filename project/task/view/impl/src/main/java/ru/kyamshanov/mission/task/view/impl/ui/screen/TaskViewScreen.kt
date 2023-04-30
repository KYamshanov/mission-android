package ru.kyamshanov.mission.task.view.impl.ui.screen

import androidx.compose.runtime.Composable
import ru.kyamshanov.mission.navigation_core.common.BoundaryDataComposableScreen
import ru.kyamshanov.mission.navigation_core.common.DestinationScreen
import ru.kyamshanov.mission.navigation_core.common.SerializableNavigationBoundaryData
import ru.kyamshanov.mission.task.view.impl.domain.model.ProjectInfo
import ru.kyamshanov.mission.task.view.impl.ui.composable.TaskViewComposable

private const val DESTINATION_KEY = "TaskViewScreen"

class TaskViewScreen() : DestinationScreen, BoundaryDataComposableScreen {

    override val destination: String
        get() = DESTINATION_KEY

    override val boundaryData: SerializableNavigationBoundaryData
        get() = requireNotNull(data)

    override val composableSupplier: @Composable (SerializableNavigationBoundaryData) -> Unit = { data ->
        val boundaryData = data as BoundaryData
        TaskViewComposable(
            taskId = boundaryData.taskId,
            projectInfo = boundaryData.projectInfoBoundary,
        )
    }

    private var data: BoundaryData? = null

    constructor(
        projectTitle: String,
        projectId: String,
        taskId: String,
    ) : this() {
        data = BoundaryData(
            taskId = taskId,
            projectInfoBoundary = ProjectInfo(
                projectId = projectId,
                projectName = projectTitle
            )
        )
    }

    private data class BoundaryData(
        val taskId: String,
        val projectInfoBoundary: ProjectInfo,
    ) : SerializableNavigationBoundaryData
}