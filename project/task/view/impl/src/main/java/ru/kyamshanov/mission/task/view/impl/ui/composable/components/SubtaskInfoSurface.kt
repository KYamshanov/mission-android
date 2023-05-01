package ru.kyamshanov.mission.task.view.impl.ui.composable.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.kyamshanov.mission.task.view.impl.domain.model.SubtaskEditingScheme
import ru.kyamshanov.mission.task.view.impl.domain.model.SubtaskInfo
import ru.kyamshanov.mission.task.view.impl.ui.model.SubtaskScreenState
import ru.kyamshanov.mission.task.view.impl.ui.viewmodel.SubtaskViewModel
import ru.kyamshanov.mission.ui_core.ui.components.Cell
import ru.kyamshanov.mission.ui_core.ui.components.Icon
import ru.kyamshanov.mission.ui_core.ui.components.MainButton
import ru.kyamshanov.mission.ui_core.ui.components.Surface
import ru.kyamshanov.mission.ui_core.ui.components.TextField
import ru.kyamshanov.mission.ui_core.ui.components.TextFieldCompose
import ru.kyamshanov.mission.ui_core.ui.components.TopBar
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@Composable
internal fun SubtaskInfoSurface(
    info: SubtaskInfo,
    viewModel: SubtaskViewModel,
    screenState: SubtaskScreenState,
) {
    val stageSelectorVisibleState = remember { mutableStateOf(false) }


    Surface(
        topContent = {
            TopBar(title = info.title, navigationListener = viewModel::clickOnBack)
        },
        bottomContent = {
            if (screenState.editingScheme.hasChanges) MainButton(
                modifier = Modifier.fillMaxWidth(),
                label = "Сохранить"
            ) { viewModel.clickOnSaveChanges() }
        },
        content = {
            SubtaskInfoCell(
                info = info,
                viewModel = viewModel,
                editingScheme = screenState.editingScheme,
                onStateSelectorRequest = {
                    stageSelectorVisibleState.value = true
                })
        }
    )

    if (stageSelectorVisibleState.value) {
        SubtaskStateSelector(
            states = SubtaskInfo.State.values().toList(),
            clickOnState = { stage ->
                stageSelectorVisibleState.value = false
                viewModel.setState(stage)
            }
        ) { stageSelectorVisibleState.value = false }
    }
}

@Composable
private fun SubtaskInfoCell(
    modifier: Modifier = Modifier,
    info: SubtaskInfo,
    viewModel: SubtaskViewModel,
    editingScheme: SubtaskEditingScheme,
    onStateSelectorRequest: () -> Unit,
) = Column(modifier = modifier.fillMaxHeight()) {

    TextFieldCompose(
        label = "Задача",
        text = info.title,
        onValueChange = viewModel::setTitle,
        editable = editingScheme.isEditableTitle
    )
    Spacer(modifier = Modifier.height(5.dp))
    TextFieldCompose(
        label = "Описание задачи",
        text = info.description,
        onValueChange = viewModel::setDescription,
        editable = editingScheme.isEditableDescription
    )
    Spacer(modifier = Modifier.height(5.dp))
    TextField(
        label = "Ответственный",
        text = info.responsible.name,
        rightIcon = if (editingScheme.isEditableResponsible) {
            {
                Image(
                    modifier = Modifier.clickable { viewModel.clickOnSearchResponsible() },
                    painter = painterResource(id = ru.kyamshanov.mission.ui_core.R.drawable.ic_search),
                    contentDescription = "Поиск",
                )
            }
        } else null,
        underlined = true
    )
    Spacer(modifier = Modifier.height(10.dp))
    Cell {
        TextField(
            modifier = Modifier.padding(5.dp).fillMaxWidth(),
            rightIcon = if (editingScheme.isStateEditable) {
                {
                    Icon(
                        modifier = Modifier.clickable { onStateSelectorRequest() },
                        iconResource = ru.kyamshanov.mission.ui_core.R.drawable.square_edit_outline,
                        contentDescription = "Редактировать состояние",
                    )
                }
            } else null,
            content = {
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(text = "Состояние: ", style = MissionTheme.typography.title)
                    SubtaskStateText(subtaskState = info.stage)
                }
            }
        )
    }

    Spacer(modifier = Modifier.height(5.dp))
    Text(
        modifier = Modifier.align(Alignment.CenterHorizontally),
        text = "Описание результатов",
        style = MissionTheme.typography.title
    )
    Spacer(modifier = Modifier.height(5.dp))

    if (editingScheme.isExecutionResultEditable.not()) {
        Text(
            modifier = Modifier.align(Alignment.Start),
            text = "Результатов ещё нет",
            style = MissionTheme.typography.titleSecondary
        )
    } else {
        Cell {
            TextFieldCompose(
                modifier = Modifier.fillMaxWidth(),
                label = "Описание результатов",
                text = info.executionResult ?: "Добавьте описание хода выполнения проекта",
                onValueChange = { viewModel.setExecutionResult(it) },
                underlined = false,
                maxLines = 10,
                editable = true
            )
        }
    }
}