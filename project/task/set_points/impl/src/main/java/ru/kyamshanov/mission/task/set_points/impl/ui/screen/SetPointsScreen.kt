package ru.kyamshanov.mission.task.set_points.impl.ui.screen

import androidx.compose.runtime.Composable
import ru.kyamshanov.mission.navigation_core.common.DestinationScreen
import ru.kyamshanov.mission.navigation_core.common.ParameterizedComposableScreen
import ru.kyamshanov.mission.project.common.domain.model.TaskId
import ru.kyamshanov.mission.task.set_points.impl.ui.composable.SetPointsComposable

private const val DESTINATION_KEY = "TaskViewScreen"
private const val TASK_ID_KEY = "taskId"
private const val MAX_POINTS_KEY = "maxPoints"

class SetPointsScreen(
    private val taskId: String? = null,
    private val maxPoints: Int? = null,
) : DestinationScreen, ParameterizedComposableScreen {

    override val parameterKeys = listOf(TASK_ID_KEY, MAX_POINTS_KEY)

    override val parametersSupplier: () -> Map<String, String?> = {
        mapOf(
            TASK_ID_KEY to taskId,
            MAX_POINTS_KEY to maxPoints.toString()
        )
    }

    override val composableSupplier: @Composable (parameters: Map<String, String?>) -> Unit =
        { parameters ->
            SetPointsComposable(
                TaskId(requireNotNull(parameters[TASK_ID_KEY])),
                requireNotNull(parameters[MAX_POINTS_KEY]).toInt()
            )
        }

    override val destination: String
        get() = DESTINATION_KEY
}