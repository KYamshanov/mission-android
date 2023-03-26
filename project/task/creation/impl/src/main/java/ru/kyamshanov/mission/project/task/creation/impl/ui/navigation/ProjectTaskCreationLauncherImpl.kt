package ru.kyamshanov.mission.project.task.creation.impl.ui.navigation

import javax.inject.Inject
import ru.kyamshanov.mission.navigation_core.api.Navigator
import ru.kyamshanov.mission.project.common.domain.model.ProjectId
import ru.kyamshanov.mission.project.task.creation.api.navigation.ProjectTaskCreationLauncher
import ru.kyamshanov.mission.project.task.creation.impl.ui.screen.ProjectTaskCreationScreen

class ProjectTaskCreationLauncherImpl @Inject constructor(
    private val navigator: Navigator,
) : ProjectTaskCreationLauncher {

    override fun launch(projectId: ProjectId) {
        navigator.navigateTo(ProjectTaskCreationScreen(projectId = projectId))
    }
}