package ru.kyamshanov.mission.project_view.impl.ui.screen

import androidx.compose.runtime.Composable
import ru.kyamshanov.mission.navigation_core.common.DestinationScreen
import ru.kyamshanov.mission.navigation_core.common.ParameterizedComposableScreen
import ru.kyamshanov.mission.project_view.impl.ui.composable.ProjectComposable

private const val DESTINATION_KEY = "ProjectScreen"

class ProjectScreen(
    private val projectId: String? = null,
) : DestinationScreen, ParameterizedComposableScreen {

    override val parameterKeys = listOf("projectId")

    override val parametersSupplier: () -> Map<String, String?> = {
        mapOf("projectId" to projectId)
    }

    override val composableSupplier: @Composable (parameters: Map<String, String?>) -> Unit =
        { parameters -> ProjectComposable(requireNotNull(parameters["projectId"]) { "ProjectId for ProjectComposable cannot be null" }) }

    override val destination: String
        get() = DESTINATION_KEY
}