package ru.kyamshanov.mission.task.view.impl.ui.screen

import androidx.compose.runtime.Composable
import ru.kyamshanov.mission.navigation_core.common.DestinationScreen
import ru.kyamshanov.mission.navigation_core.common.ParameterizedComposableScreen
import ru.kyamshanov.mission.task.view.impl.ui.composable.SubtaskViewComposable

private const val DESTINATION_KEY = "SubtaskCreationScreen"
private const val SUBTASK_ID_KEY = "subtaskId"

class SubtaskViewScreen(
    private val subtaskId: String? = null,
) : DestinationScreen, ParameterizedComposableScreen {

    override val parameterKeys = listOf(SUBTASK_ID_KEY)

    override val parametersSupplier: () -> Map<String, String?> = {
        mapOf(SUBTASK_ID_KEY to subtaskId)
    }

    override val composableSupplier: @Composable (parameters: Map<String, String?>) -> Unit = { parameters ->
        SubtaskViewComposable(
            subtaskId = requireNotNull(parameters[SUBTASK_ID_KEY]) { "TaskId for TaskViewComposable cannot be null" }
        )
    }

    override val destination: String
        get() = DESTINATION_KEY
}