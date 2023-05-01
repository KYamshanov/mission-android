package ru.kyamshanov.mission.task.view.impl.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.task.view.api.di.TaskViewComponent
import ru.kyamshanov.mission.task.view.impl.di.ModuleComponent
import ru.kyamshanov.mission.task.view.impl.domain.model.ProjectInfo
import ru.kyamshanov.mission.task.view.impl.ui.composable.components.TaskInfoSurface
import ru.kyamshanov.mission.task.view.impl.ui.viewmodel.TaskViewModel
import ru.kyamshanov.mission.ui_core.ui.components.Loader
import ru.kyamshanov.mission.ui_core.ui.components.SomethingWentWrongDialog

@Composable
internal fun TaskViewComposable(
    taskId: String,
    projectInfo: ProjectInfo,
    moduleComponent: ModuleComponent = requireNotNull(Di.getInternalComponent<TaskViewComponent, ModuleComponent>()),
    viewModel: TaskViewModel = viewModel {
        moduleComponent.taskViewModelFactory.create(
            taskId = taskId,
            projectInfo = projectInfo
        )
    },
) {

    val screenState = viewModel.screenState.collectAsState()

    if (screenState.value.loading) Loader { viewModel.onBack() }
    else screenState.value.taskInfo?.let { taskInfo ->
        TaskInfoSurface(
            screenState = screenState.value,
            viewModel = viewModel,
            projectTitle = projectInfo.projectName,
            taskInfo = taskInfo
        )
    } ?: SomethingWentWrongDialog(onDismissRequest = { viewModel.onBack() })
}