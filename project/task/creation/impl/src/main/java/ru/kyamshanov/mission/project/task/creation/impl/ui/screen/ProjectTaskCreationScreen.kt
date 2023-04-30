package ru.kyamshanov.mission.project.task.creation.impl.ui.screen

import androidx.compose.runtime.Composable
import ru.kyamshanov.mission.navigation_core.common.DestinationScreen
import ru.kyamshanov.mission.navigation_core.common.ParameterizedComposableScreen
import ru.kyamshanov.mission.project.common.domain.model.ProjectId
import ru.kyamshanov.mission.project.task.creation.impl.ui.composable.ProjectTaskCreationComposable

private const val DESTINATION_KEY = "ProjectTaskCreationScreen"

class ProjectTaskCreationScreen(
    private val projectId: ProjectId?,
    private val projectName: String?,
) : ParameterizedComposableScreen, DestinationScreen {

    override val parameterKeys: List<String> = listOf(PROJECT_ID_PARAM, PROJECT_NAME_PARAM)

    override val parametersSupplier: () -> Map<String, String?> = {
        mapOf(PROJECT_ID_PARAM to projectId?.value, PROJECT_NAME_PARAM to projectName)
    }

    override val destination: String
        get() = DESTINATION_KEY

    override val composableSupplier: @Composable (Map<String, String?>) -> Unit =
        @Composable { parameters ->
            val projectId = requireNotNull(parameters[PROJECT_ID_PARAM])
            val projectName = requireNotNull(parameters[PROJECT_NAME_PARAM])
            ProjectTaskCreationComposable(ProjectId(projectId), projectName)
        }

    constructor() : this(null, null)

    companion object {

        private const val PROJECT_ID_PARAM = "projectId"
        private const val PROJECT_NAME_PARAM = "projectName"
    }
}