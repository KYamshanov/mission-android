package ru.kyamshanov.mission.project_view.impl.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.kyamshanov.mission.project_view.impl.R
import ru.kyamshanov.mission.project_view.impl.domain.model.ProjectStage
import ru.kyamshanov.mission.project_view.impl.domain.model.SlimTaskInfo
import ru.kyamshanov.mission.project_view.impl.domain.usecase.TaskStagePresentUseCase
import ru.kyamshanov.mission.project_view.impl.ui.model.ProjectScreenState
import ru.kyamshanov.mission.project_view.impl.ui.viewmodel.ProjectViewModel
import ru.kyamshanov.mission.ui_core.ui.components.Cell
import ru.kyamshanov.mission.ui_core.ui.components.SecondaryButton
import ru.kyamshanov.mission.ui_core.ui.components.TextFieldCompose
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@Composable
internal fun ProjectViewComposable(
    projectInfo: ProjectScreenState.ProjectInfo,
    viewModel: ProjectViewModel,
    taskStagePresentUseCase: TaskStagePresentUseCase,
) =
    Column(
        modifier = Modifier.padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        TextFieldCompose(
            label = stringResource(id = R.string.pv_project_name_title),
            text = projectInfo.title.text,
            onValueChange = {},
            editable = projectInfo.title.editable,
        )

        TextFieldCompose(
            label = stringResource(id = R.string.pv_project_description_title),
            text = projectInfo.description.text,
            onValueChange = {},
            editable = projectInfo.description.editable,
        )

        Spacer(modifier = Modifier.height(3.dp))
        ProjectStageText(projectInfo.projectStage)
        Spacer(modifier = Modifier.height(3.dp))
        SecondaryButton(
            label = stringResource(id = R.string.pv_participants, projectInfo.participantsCount),
            onClick = {}
        )
        Spacer(modifier = Modifier.height(3.dp))
        for (task in projectInfo.tasks) {
            TaskCellComposable(task, taskStagePresentUseCase)
        }

        Button(onClick = { viewModel.createTask() }) {
            Text(text = "Add stage")
        }
    }

@Composable
private fun TaskCellComposable(
    taskInfo: SlimTaskInfo,
    taskStagePresentUseCase: TaskStagePresentUseCase,
) = Cell(modifier = Modifier.padding(5.dp)) {
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