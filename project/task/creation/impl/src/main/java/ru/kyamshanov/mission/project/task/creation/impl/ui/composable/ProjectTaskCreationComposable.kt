package ru.kyamshanov.mission.project.task.creation.impl.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.project.common.domain.model.ProjectId
import ru.kyamshanov.mission.project.task.creation.api.di.ProjectTaskCreationComponent
import ru.kyamshanov.mission.project.task.creation.impl.R
import ru.kyamshanov.mission.project.task.creation.impl.di.ModuleComponent
import ru.kyamshanov.mission.project.task.creation.impl.ui.viewmodel.TaskCreationViewModel
import ru.kyamshanov.mission.ui_core.ui.components.CellDate
import ru.kyamshanov.mission.ui_core.ui.components.CellInput
import ru.kyamshanov.mission.ui_core.ui.components.MainButton
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@Composable
internal fun ProjectTaskCreationComposable(
    projectId: ProjectId,
    moduleComponent: ModuleComponent = requireNotNull(Di.getInternalComponent<ProjectTaskCreationComponent, ModuleComponent>()),
    taskCreationViewModel: TaskCreationViewModel = viewModel { moduleComponent.taskCreationViewModel },
) = Scaffold(
    backgroundColor = MissionTheme.colors.background,
    bottomBar = {
        MainButton(
            label = stringResource(id = R.string.task_add_stage),
            onClick = {

            })
    }, content = {

        val screenState = taskCreationViewModel.screenState.collectAsState()

        val taskState = screenState.value.taskCreationInfo

        Column {
            CellInput(
                value = taskState.title,
                onValueChange = taskCreationViewModel::setTitle,
                label = stringResource(id = R.string.task_stage_name_title)
            )

            CellInput(
                value = taskState.description,
                onValueChange = taskCreationViewModel::setDescription,
                label = stringResource(id = R.string.task_stage_description_title)
            )

            CellDate(
                value = taskState.startAt,
                label = stringResource(id = R.string.task_stage_start_at_title),
                onValueChange = taskCreationViewModel::setStartAt,
                missionDateFormatter = moduleComponent.dateFormatterProvider.cellDateFormatter()
            )

            CellDate(
                value = taskState.endAt,
                label = stringResource(id = R.string.task_stage_start_at_title),
                onValueChange = taskCreationViewModel::setEndAt,
                missionDateFormatter = moduleComponent.dateFormatterProvider.cellDateFormatter()
            )

            CellInput(
                value = taskState.maxPoints?.toString().orEmpty(),
                onValueChange = {
                    it.toIntOrNull()
                        ?.let { maxPoints: Int -> taskCreationViewModel.setMaxPoints(maxPoints) }
                },
                label = stringResource(id = R.string.task_stage_description_title)
            )
        }

    })