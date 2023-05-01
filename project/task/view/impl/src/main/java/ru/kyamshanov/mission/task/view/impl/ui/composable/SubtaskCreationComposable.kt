package ru.kyamshanov.mission.task.view.impl.ui.composable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.task.view.api.di.TaskViewComponent
import ru.kyamshanov.mission.task.view.impl.di.ModuleComponent
import ru.kyamshanov.mission.task.view.impl.domain.model.ProjectInfo
import ru.kyamshanov.mission.task.view.impl.ui.viewmodel.SubtaskCreationViewModel
import ru.kyamshanov.mission.ui_core.R
import ru.kyamshanov.mission.ui_core.ui.components.Cell
import ru.kyamshanov.mission.ui_core.ui.components.CellDate
import ru.kyamshanov.mission.ui_core.ui.components.CellInput
import ru.kyamshanov.mission.ui_core.ui.components.Icon
import ru.kyamshanov.mission.ui_core.ui.components.MainButton
import ru.kyamshanov.mission.ui_core.ui.components.Surface
import ru.kyamshanov.mission.ui_core.ui.components.TextField
import ru.kyamshanov.mission.ui_core.ui.components.TopBar

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

    Surface(
        topContent = {
            TopBar(
                title = "Создание задачи",
                subtitle = projectInfo.projectName
            ) { viewModel.clickOnBack() }
        },
        bottomContent = {
            MainButton(
                modifier = Modifier.fillMaxWidth(),
                label = "Добавить задачу",
                onClick = viewModel::onCreate
            )
        },
    ) {
        CellInput(
            value = subtaskInfo.title,
            onValueChange = { viewModel.setTitle(it) },
            label = "Введите название задачи"
        )
        Spacer(modifier = Modifier.height(5.dp))
        CellInput(
            value = subtaskInfo.description,
            onValueChange = { viewModel.setDescription(it) },
            label = "Введите описание задачи",
            maxLines = Int.MAX_VALUE
        )
        Spacer(modifier = Modifier.height(5.dp))
        CellDate(
            value = subtaskInfo.startAt,
            onValueChange = { viewModel.setStartAt(it) },
            label = "Начало выполнения задачи",
            editable = true,
            missionDateFormatter = subtaskInfo.dateFormatter,
        )
        Spacer(modifier = Modifier.height(5.dp))
        CellDate(
            value = subtaskInfo.endAt,
            onValueChange = { viewModel.setEndAt(it) },
            label = "Завершение выполнения задачи",
            editable = true,
            missionDateFormatter = subtaskInfo.dateFormatter,
        )
        Spacer(modifier = Modifier.height(5.dp))
        Cell {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                label = "Ответственный",
                text = subtaskInfo.responsible?.name ?: "Не выбран",
                rightIcon = {
                    Icon(
                        iconResource = R.drawable.ic_search,
                        contentDescription = "Поиск",
                        onClick = viewModel::findResponsible
                    )
                },
                underlined = false
            )
        }
    }
}