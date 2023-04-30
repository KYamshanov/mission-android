package ru.kyamshanov.mission.task.view.impl.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.task.view.api.di.TaskViewComponent
import ru.kyamshanov.mission.task.view.impl.di.ModuleComponent
import ru.kyamshanov.mission.task.view.impl.domain.model.ProjectInfo
import ru.kyamshanov.mission.task.view.impl.ui.viewmodel.SubtaskCreationViewModel
import ru.kyamshanov.mission.ui_core.ui.components.Cell
import ru.kyamshanov.mission.ui_core.ui.components.CellInput
import ru.kyamshanov.mission.ui_core.ui.components.DateField
import ru.kyamshanov.mission.ui_core.ui.components.MainButton

private const val TAG = "SubtaskCreationComposable"

@Composable
internal fun SubtaskCreationComposable(
    taskId: String,
    projectInfo: ProjectInfo,
    moduleComponent: ModuleComponent = requireNotNull(Di.getInternalComponent<TaskViewComponent, ModuleComponent>()),
    viewModel: SubtaskCreationViewModel = viewModel {
        moduleComponent.subtaskCreationViewModelFactory.create(taskId, projectInfo)
    },
) {
    val screenState = viewModel.screenState.collectAsState()
    val subtaskInfo = screenState.value.subtaskInfo

    LaunchedEffect(true) { viewModel.obtainResponsible() }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {

        Cell {
            CellInput(
                value = subtaskInfo.title,
                onValueChange = { viewModel.setTitle(it) },
                label = "Введите название задачи"
            )
            CellInput(
                value = subtaskInfo.description,
                onValueChange = { viewModel.setDescription(it) },
                label = "Введите описание задачи",
                maxLines = Int.MAX_VALUE
            )
            DateField(
                value = subtaskInfo.startAt,
                onValueChange = { viewModel.setStartAt(it) },
                label = "Начало выполнения задачи",
                missionDateFormatter = subtaskInfo.dateFormatter,
            )
            DateField(
                value = subtaskInfo.endAt,
                onValueChange = { viewModel.setEndAt(it) },
                label = "Завершение выполнения задачи",
                missionDateFormatter = subtaskInfo.dateFormatter,
            )

            CellInput(
                modifier = Modifier.clickable { viewModel.findResponsible() },
                value = subtaskInfo.responsible?.name ?: "Не выбран",
                onValueChange = {},
                label = "Ответственный",
                editable = false
            )

            MainButton(
                label = "Добавить задачу",
                onClick = viewModel::onCreate
            )
        }
    }
}