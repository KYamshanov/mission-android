package ru.kyamshanov.mission.task.view.impl.ui.composable.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.kyamshanov.mission.task.view.impl.R
import ru.kyamshanov.mission.task.view.impl.domain.model.SubtaskInfo
import ru.kyamshanov.mission.task.view.impl.domain.model.TaskInfo
import ru.kyamshanov.mission.task.view.impl.ui.model.TaskViewScreenState
import ru.kyamshanov.mission.task.view.impl.ui.viewmodel.TaskViewModel
import ru.kyamshanov.mission.ui_core.ui.components.AlternativeButton
import ru.kyamshanov.mission.ui_core.ui.components.ComplexCell
import ru.kyamshanov.mission.ui_core.ui.components.DateField
import ru.kyamshanov.mission.ui_core.ui.components.MainButton
import ru.kyamshanov.mission.ui_core.ui.components.SecondaryButton
import ru.kyamshanov.mission.ui_core.ui.components.Surface
import ru.kyamshanov.mission.ui_core.ui.components.TextField
import ru.kyamshanov.mission.ui_core.ui.components.TextFieldCompose
import ru.kyamshanov.mission.ui_core.ui.components.TopBar
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@Composable
internal fun TaskInfoSurface(
    projectTitle: String,
    taskInfo: TaskInfo,
    screenState: TaskViewScreenState,
    viewModel: TaskViewModel,
) = Surface(
    topContent = {
        TopBar(
            title = projectTitle,
            navigationListener = viewModel::onBack,
            subtitle = "Этап - ${taskInfo.title}"
        )
    },
    bottomContent = {
        if (screenState.taskEditingScheme?.isEditableSubtasks == true) {
            MainButton(
                modifier = Modifier.fillMaxWidth(),
                label = "Добавить задачу",
                onClick = { viewModel.createSubtask() }
            )
        }
        if (screenState.taskEditingScheme?.isPointsEditable == true) {
            Spacer(modifier = Modifier.height(10.dp))
            SecondaryButton(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(id = R.string.tv_set_points_title),
                onClick = viewModel::openSetPointScreen
            )
        }
    }
) {
    Column {
        TaskInfoCell(taskInfo, screenState, viewModel)
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Задачи",
                style = MissionTheme.typography.title,
                modifier = Modifier.align(Alignment.Center)
            )
            if (screenState.taskEditingScheme?.hasChanges == true) {
                AlternativeButton(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    label = "Сохранить",
                    onClick = viewModel::saveChanges
                )
            }
        }
        if (screenState.subtaskLoading) Text(text = "Загрузка...")
        else screenState.subtasks?.takeIf { it.isNotEmpty() }?.let { subtasks ->
            subtasks.forEach { subtaskInfo ->
                Spacer(modifier = Modifier.height(5.dp))
                SubtaskInfoCell(
                    modifier = Modifier.clickable { viewModel.openSubtask(subtaskInfo.subtaskId) },
                    info = subtaskInfo
                )
            }
        } ?: Text(text = "Задачи не созданы")
    }
}

@Composable
private fun TaskInfoCell(
    info: TaskInfo,
    screenInfo: TaskViewScreenState,
    viewModel: TaskViewModel,
) = Column {

    TextFieldCompose(
        text = info.title,
        label = stringResource(id = R.string.tv_title),
        editable = screenInfo.taskEditingScheme?.isEditableTitle ?: false,
        onValueChange = viewModel::setTitle
    )
    Spacer(modifier = Modifier.height(5.dp))
    TextFieldCompose(
        text = info.description,
        label = stringResource(id = R.string.tv_description),
        editable = screenInfo.taskEditingScheme?.isEditableDescription ?: false,
        onValueChange = viewModel::setDescription,
        maxLines = 50
    )
    Spacer(modifier = Modifier.height(10.dp))
    ComplexCell {
        item {
            TextField(
                content = {
                    Row {
                        Text(text = "Статус:", style = MissionTheme.typography.inputText)
                        Spacer(modifier = Modifier.width(5.dp))
                        TaskStateText(state = info.state)
                    }
                }
            )
        }
        item {
            DateField(
                value = info.startAt,
                label = stringResource(id = R.string.tv_start_at),
                missionDateFormatter = info.dateFormatter,
                editable = screenInfo.taskEditingScheme?.isEditableStartAt ?: false,
                onValueChange = viewModel::setStartAt
            )
        }
        item {
            DateField(
                value = info.endAt,
                label = stringResource(id = R.string.tv_end_at),
                missionDateFormatter = info.dateFormatter,
                editable = screenInfo.taskEditingScheme?.isEditableEndAt ?: false,
                onValueChange = viewModel::setEndAt
            )
        }
        item {
            TextFieldCompose(
                modifier = Modifier.fillMaxWidth(),
                text = info.maxPoints.toString(),
                label = "Максимальное кол-во баллов",
                editable = screenInfo.taskEditingScheme?.isEditableMaxPoints ?: false,
                onValueChange = { it.toIntOrNull()?.let { maxPoints -> viewModel.setMaxPoints(maxPoints) } },
                underlined = false
            )
        }
    }
}

@Composable
private fun SubtaskInfoCell(
    modifier: Modifier = Modifier,
    info: SubtaskInfo,
) = ComplexCell(modifier = modifier) {
    item(divided = false) {
        TextField(
            label = "Название задачи",
            text = info.title,
        )
    }

    item(divided = false) {
        TextField(
            text = info.description,
            label = "Описание задачи",
        )
    }
    item(divided = false) {

        TextField(
            label = "Состояние задачи",
            content = { SubtaskStateText(subtaskState = info.stage) },
            underlined = true
        )
    }
    item {
        TextField(
            text = "Ответственный: ${info.responsible.name}",
        )

    }

    item {
        TextField(
            label = "Сроки выполнения",
            text = "${info.dateFormatter(info.startAt)} - ${info.dateFormatter(info.endAt)}",
        )
    }
}