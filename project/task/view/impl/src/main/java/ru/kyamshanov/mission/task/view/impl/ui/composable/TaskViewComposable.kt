package ru.kyamshanov.mission.task.view.impl.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.task.view.api.di.TaskViewComponent
import ru.kyamshanov.mission.task.view.impl.di.ModuleComponent
import ru.kyamshanov.mission.task.view.impl.di.ViewModelSupplier
import ru.kyamshanov.mission.task.view.impl.ui.composable.components.TaskInfoLoading

@Composable
internal fun TaskViewComposable(
    taskId: String,
    moduleComponent: ModuleComponent = requireNotNull(Di.getInternalComponent<TaskViewComponent, ModuleComponent>()),
    viewModelSupplier: ViewModelSupplier = moduleComponent.viewModelSupplier,
) {
    val viewModel = viewModel { viewModelSupplier.createTaskViewModel(taskId = taskId) }
    val screenState = viewModel.screenState.collectAsState()

    if (screenState.value.loading) TaskInfoLoading()
    else screenState.value.taskInfo?.let { TaskInfoSurface(info = it, viewModel = viewModel) }
        ?: throw IllegalStateException("Screen state is not loading and has not taskInfo")
}