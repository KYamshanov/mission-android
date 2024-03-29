package ru.kyamshanov.mission.task.view.impl.ui.composable

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.task.view.api.di.TaskViewComponent
import ru.kyamshanov.mission.task.view.impl.di.ModuleComponent
import ru.kyamshanov.mission.task.view.impl.ui.composable.components.SubtaskInfoSurface
import ru.kyamshanov.mission.task.view.impl.ui.viewmodel.SubtaskViewModel
import ru.kyamshanov.mission.ui_core.ui.components.Loader

private const val TAG = "SubtaskViewComposable"

@Composable
internal fun SubtaskViewComposable(
    subtaskId: String,
    moduleComponent: ModuleComponent = requireNotNull(Di.getInternalComponent<TaskViewComponent, ModuleComponent>()),
    viewModel: SubtaskViewModel = viewModel { moduleComponent.subtaskViewModelFactory.create(subtask = subtaskId) },
) {
    val screenState = viewModel.screenStateFlow.collectAsState()

    if (screenState.value.loading) Loader { viewModel.clickOnBack() }
    else screenState.value.subtaskInfo?.let {
        SubtaskInfoSurface(
            info = it,
            viewModel = viewModel,
            screenState = screenState.value
        )
    } ?: Log.d(TAG, "Screen state is not loading and has not taskInfo")
}

