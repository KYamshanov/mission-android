package ru.kyamshanov.mission.task.set_points.impl.ui.navigation

import javax.inject.Inject
import ru.kyamshanov.mission.navigation_core.api.Navigator
import ru.kyamshanov.mission.project.common.domain.model.TaskId
import ru.kyamshanov.mission.task.set_points.api.navigation.SetPointsLauncher
import ru.kyamshanov.mission.task.set_points.impl.ui.screen.SetPointsScreen

internal class SetPointsLauncherImpl @Inject constructor(
    private val navigator: Navigator,
) : SetPointsLauncher {

    override fun launch(taskId: TaskId, maxPoints: Int) {
        navigator.navigateTo(SetPointsScreen(taskId.value, maxPoints))
    }
}