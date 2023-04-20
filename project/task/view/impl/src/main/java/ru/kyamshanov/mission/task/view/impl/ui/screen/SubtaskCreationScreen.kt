package ru.kyamshanov.mission.task.view.impl.ui.screen

import androidx.compose.runtime.Composable
import ru.kyamshanov.mission.navigation_core.common.DestinationScreen
import ru.kyamshanov.mission.navigation_core.common.ParameterizedComposableScreen
import ru.kyamshanov.mission.task.view.impl.ui.composable.SubtaskCreationComposable

private const val DESTINATION_KEY = "SubtaskCreationScreen"
private const val TASK_ID_KEY = "taskId"

class SubtaskCreationScreen(
    private val taskId: String? = null,
) : DestinationScreen, ParameterizedComposableScreen {

    override val parameterKeys = listOf(TASK_ID_KEY)

    override val parametersSupplier: () -> Map<String, String?> = {
        mapOf(TASK_ID_KEY to taskId)
    }

    override val composableSupplier: @Composable (parameters: Map<String, String?>) -> Unit = { parameters ->
        SubtaskCreationComposable(
            taskId = requireNotNull(parameters[TASK_ID_KEY]) { "TaskId for TaskViewComposable cannot be null" }
        )
    }

    override val destination: String
        get() = DESTINATION_KEY
}