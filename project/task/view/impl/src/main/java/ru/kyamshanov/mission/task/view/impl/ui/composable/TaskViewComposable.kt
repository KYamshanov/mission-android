package ru.kyamshanov.mission.task.view.impl.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.task.view.api.di.TaskViewComponent
import ru.kyamshanov.mission.task.view.impl.di.ModuleComponent
import ru.kyamshanov.mission.task.view.impl.ui.composable.components.TaskInfoLoading
import ru.kyamshanov.mission.task.view.impl.ui.composable.components.TaskInfoSurface
import ru.kyamshanov.mission.task.view.impl.ui.viewmodel.TaskViewModel

@Composable
internal fun TaskViewComposable(
    taskId: String,
    projectTitle: String,
    moduleComponent: ModuleComponent = requireNotNull(Di.getInternalComponent<TaskViewComponent, ModuleComponent>()),
    viewModel: TaskViewModel = viewModel { moduleComponent.taskViewModelFactory.create(taskId = taskId) },
) {

    val screenState = viewModel.screenState.collectAsState()

    if (screenState.value.loading) TaskInfoLoading()
    else TaskInfoSurface(screenState = screenState.value, viewModel = viewModel, projectTitle = projectTitle)
}