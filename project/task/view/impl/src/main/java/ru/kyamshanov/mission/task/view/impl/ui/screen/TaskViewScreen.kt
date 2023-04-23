package ru.kyamshanov.mission.task.view.impl.ui.screen

import androidx.compose.runtime.Composable
import ru.kyamshanov.mission.navigation_core.common.DestinationScreen
import ru.kyamshanov.mission.navigation_core.common.ParameterizedComposableScreen
import ru.kyamshanov.mission.task.view.impl.ui.composable.TaskViewComposable

private const val DESTINATION_KEY = "TaskViewScreen"
private const val TASK_ID_KEY = "taskId"
private const val PROJECT_TITLE_KEY = "projectTitle"

class TaskViewScreen(
    private val projectTitle: String? = null,
    private val taskId: String? = null,
) : DestinationScreen, ParameterizedComposableScreen {

    override val parameterKeys = listOf(TASK_ID_KEY, PROJECT_TITLE_KEY)

    override val parametersSupplier: () -> Map<String, String?> = {
        mapOf(TASK_ID_KEY to taskId, PROJECT_TITLE_KEY to projectTitle)
    }

    override val composableSupplier: @Composable (parameters: Map<String, String?>) -> Unit =
        { parameters ->
            TaskViewComposable(
                taskId = requireNotNull(parameters[TASK_ID_KEY]) { "TaskId for TaskViewComposable cannot be null" },
                projectTitle = requireNotNull(parameters[PROJECT_TITLE_KEY]) { "Project title for TaskViewComposable cannot be null" },
            )
        }

    override val destination: String
        get() = DESTINATION_KEY
}