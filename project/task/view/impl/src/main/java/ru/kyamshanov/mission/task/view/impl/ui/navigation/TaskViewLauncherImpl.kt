package ru.kyamshanov.mission.task.view.impl.ui.navigation

import javax.inject.Inject
import ru.kyamshanov.mission.navigation_core.api.Navigator
import ru.kyamshanov.mission.project.common.domain.model.TaskId
import ru.kyamshanov.mission.task.view.api.navigation.TaskViewLauncher
import ru.kyamshanov.mission.task.view.impl.ui.screen.TaskViewScreen

class TaskViewLauncherImpl @Inject constructor(
    private val navigator: Navigator,
) : TaskViewLauncher {

    override fun launch(taskId: TaskId) {
        navigator.navigateTo(TaskViewScreen(taskId.value))
    }
}