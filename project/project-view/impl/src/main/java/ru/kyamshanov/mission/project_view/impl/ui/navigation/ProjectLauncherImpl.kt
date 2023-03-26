package ru.kyamshanov.mission.project_view.impl.ui.navigation

import ru.kyamshanov.mission.navigation_core.api.Navigator
import ru.kyamshanov.mission.project_view.api.navigation.ProjectLauncher
import ru.kyamshanov.mission.project_view.impl.ui.screen.ProjectScreen
import javax.inject.Inject

class ProjectLauncherImpl @Inject constructor(
    private val navigator: Navigator
) : ProjectLauncher {

    override fun launch(projectId : String) {
        navigator.navigateTo(ProjectScreen(projectId = projectId))
    }
}