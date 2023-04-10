package ru.kyamshanov.mission.task.set_points.impl.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.project.common.domain.model.TaskId
import ru.kyamshanov.mission.task.set_points.api.di.SetPointsComponent
import ru.kyamshanov.mission.task.set_points.impl.di.ModuleComponent
import ru.kyamshanov.mission.task.set_points.impl.di.ViewModelSupplier
import ru.kyamshanov.mission.ui_core.ui.components.CellInput
import ru.kyamshanov.mission.ui_core.ui.components.MainButton

@Composable
internal fun SetPointsComposable(
    taskId: TaskId,
    maxPoints: Int,
    moduleComponent: ModuleComponent = requireNotNull(Di.getInternalComponent<SetPointsComponent, ModuleComponent>()),
    viewModelSupplier: ViewModelSupplier = moduleComponent.viewModelSupplier,
) {
    val viewModel = viewModel { viewModelSupplier.getSetPointsViewModel(taskId = taskId.value, maxPoints) }
    val screenState by viewModel.screenState.collectAsState()

    Column {
        CellInput(
            value = screenState.points?.toString().orEmpty(),
            onValueChange = { it.toIntOrNull()?.let { points -> viewModel.changePoints(points) } },
            label = "Баллов"
        )
        MainButton(
            label = "Выставить",
            onClick = { viewModel.push() }
        )
    }
}