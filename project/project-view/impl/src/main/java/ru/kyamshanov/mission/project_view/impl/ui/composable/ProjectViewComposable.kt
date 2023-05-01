package ru.kyamshanov.mission.project_view.impl.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.kyamshanov.mission.project_view.impl.R
import ru.kyamshanov.mission.project_view.impl.domain.model.ProjectInfo
import ru.kyamshanov.mission.project_view.impl.domain.model.ProjectStage
import ru.kyamshanov.mission.project_view.impl.domain.model.SlimTaskInfo
import ru.kyamshanov.mission.project_view.impl.domain.usecase.TaskStagePresentUseCase
import ru.kyamshanov.mission.project_view.impl.ui.model.ProjectScreenState
import ru.kyamshanov.mission.project_view.impl.ui.viewmodel.ProjectViewModel
import ru.kyamshanov.mission.ui_core.ui.components.AlternativeButton
import ru.kyamshanov.mission.ui_core.ui.components.Cell
import ru.kyamshanov.mission.ui_core.ui.components.SecondaryButton
import ru.kyamshanov.mission.ui_core.ui.components.Surface
import ru.kyamshanov.mission.ui_core.ui.components.TextFieldCompose
import ru.kyamshanov.mission.ui_core.ui.components.TopBar
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@Composable
internal fun ProjectViewComposable(
    screenState: ProjectScreenState,
    projectInfo: ProjectInfo,
    viewModel: ProjectViewModel,
    taskStagePresentUseCase: TaskStagePresentUseCase,
) = Surface(
    topContent = { TopBar(title = "Проектная деятельность", navigationListener = viewModel::onBack) },
) {
    Column {
        TextFieldCompose(
            label = stringResource(id = R.string.pv_project_name_title),
            text = projectInfo.title,
            onValueChange = viewModel::setTitle,
            editable = screenState.editingScheme.isEditableTitle,
        )

        TextFieldCompose(
            label = stringResource(id = R.string.pv_project_description_title),
            text = projectInfo.description,
            onValueChange = viewModel::setDescription,
            editable = screenState.editingScheme.isEditableDescription,
        )

        Spacer(modifier = Modifier.height(3.dp))
        ProjectStageText(projectInfo.projectStage)
        Spacer(modifier = Modifier.height(3.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            SecondaryButton(
                modifier = Modifier.align(Alignment.TopStart),
                label = stringResource(id = R.string.pv_participants, screenState.participantsCount),
                onClick = viewModel::clickOnParticipants
            )
            if (screenState.editingScheme.hasChanges) {
                AlternativeButton(
                    modifier = Modifier.align(Alignment.TopEnd),
                    label = "Сохранить",
                    onClick = viewModel::saveChanges
                )
            }
        }
        Spacer(modifier = Modifier.height(3.dp))
        for (task in projectInfo.tasks) {
            TaskCellComposable(task, taskStagePresentUseCase) { viewModel.openTask(task.id) }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row {
            SecondaryButton(
                label = "Всего ${screenState.totalPointsInfo.totalPoints} баллов",
                onClick = viewModel::clickOnPoints
            )
            Spacer(modifier = Modifier.weight(1f))
            if (screenState.editingScheme.isEditableStages) {
                AlternativeButton(
                    modifier = Modifier.align(Alignment.Top),
                    label = "Добавить этап",
                    onClick = viewModel::createTask
                )
            }
        }
    }
}

@Composable
private fun TaskCellComposable(
    taskInfo: SlimTaskInfo,
    taskStagePresentUseCase: TaskStagePresentUseCase,
    clickListener: () -> Unit,
) = Cell(modifier = Modifier.padding(5.dp).fillMaxWidth().clickable(onClick = clickListener)) {
    Text(text = stringResource(R.string.pv_stage), style = MissionTheme.typography.inputHint)
    Text(text = taskInfo.title, style = MissionTheme.typography.inputText)
    Spacer(modifier = Modifier.height(5.dp))

    Text(text = stringResource(R.string.pv_description), style = MissionTheme.typography.inputHint)
    Text(text = taskInfo.description, style = MissionTheme.typography.inputText)
    Spacer(modifier = Modifier.height(5.dp))

    Text(text = stringResource(R.string.pv_state), style = MissionTheme.typography.inputHint)
    Text(text = taskStagePresentUseCase(taskInfo.taskStage), style = MissionTheme.typography.inputText)
    Spacer(modifier = Modifier.height(5.dp))
}

@Composable
private fun ProjectStageText(
    projectStage: ProjectStage,
) = when (projectStage) {
    ProjectStage.Finished -> Text(
        text = "${stringResource(id = R.string.pv_stage)} ${stringResource(id = R.string.pv_finished)}",
        style = MissionTheme.typography.red + MissionTheme.typography.medium
    )
    is ProjectStage.InProject -> Text(
        text = "${stringResource(id = R.string.pv_stage)} ${stringResource(id = R.string.pv_in_progress)}",
        style = MissionTheme.typography.green + MissionTheme.typography.medium
    )
    ProjectStage.Wait -> Text(
        text = "${stringResource(id = R.string.pv_stage)} ${stringResource(id = R.string.pv_wait)}",
        style = MissionTheme.typography.green + MissionTheme.typography.medium
    )
}