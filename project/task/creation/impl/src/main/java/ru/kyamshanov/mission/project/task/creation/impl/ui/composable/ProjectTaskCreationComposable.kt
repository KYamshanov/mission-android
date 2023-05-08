package ru.kyamshanov.mission.project.task.creation.impl.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.project.common.domain.model.ProjectId
import ru.kyamshanov.mission.project.task.creation.api.di.ProjectTaskCreationComponent
import ru.kyamshanov.mission.project.task.creation.impl.R
import ru.kyamshanov.mission.project.task.creation.impl.di.ModuleComponent
import ru.kyamshanov.mission.project.task.creation.impl.ui.viewmodel.TaskCreationViewModel
import ru.kyamshanov.mission.project.task.creation.impl.ui.viewmodel.ViewModelProvider
import ru.kyamshanov.mission.ui_core.ui.components.CellDate
import ru.kyamshanov.mission.ui_core.ui.components.CellInput
import ru.kyamshanov.mission.ui_core.ui.components.MainButton
import ru.kyamshanov.mission.ui_core.ui.components.Surface
import ru.kyamshanov.mission.ui_core.ui.components.TopBar

@Composable
internal fun ProjectTaskCreationComposable(
    projectId: ProjectId,
    projectName: String,
    moduleComponent: ModuleComponent = requireNotNull(Di.getInternalComponent<ProjectTaskCreationComponent, ModuleComponent>()),
    viewModelProvider: ViewModelProvider = moduleComponent.viewModelProvider,
    taskCreationViewModel: TaskCreationViewModel = viewModel { viewModelProvider.createTaskCreationViewModel(projectId.value) },
) = Surface(
    topContent = {
        TopBar(
            title = "Создание этапа",
            subtitle = projectName,
            navigationListener = taskCreationViewModel::clickOnBack
        )
    },
    bottomContent = {
        Box(modifier = Modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
            MainButton(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(id = R.string.task_add_stage),
                onClick = { taskCreationViewModel.save() }
            )
        }
    }, content = {

        val screenState = taskCreationViewModel.screenState.collectAsState()

        val taskState = screenState.value.taskCreationInfo

        Column {
            CellInput(
                value = taskState.title,
                onValueChange = taskCreationViewModel::setTitle,
                label = stringResource(id = R.string.task_stage_name_title)
            )
            Spacer(modifier = Modifier.height(10.dp))
            CellInput(
                value = taskState.description,
                onValueChange = taskCreationViewModel::setDescription,
                label = stringResource(id = R.string.task_stage_description_title),
                maxLines = 50,
            )
            Spacer(modifier = Modifier.height(10.dp))
            CellDate(
                value = taskState.startAt,
                label = stringResource(id = R.string.task_stage_start_at_title),
                onValueChange = taskCreationViewModel::setStartAt,
                missionDateFormatter = moduleComponent.dateFormatterProvider.cellDateFormatter(),
                editable = true
            )
            Spacer(modifier = Modifier.height(10.dp))
            CellDate(
                value = taskState.endAt,
                label = stringResource(id = R.string.task_stage_end_at_title),
                onValueChange = taskCreationViewModel::setEndAt,
                missionDateFormatter = moduleComponent.dateFormatterProvider.cellDateFormatter(),
                editable = true
            )
            Spacer(modifier = Modifier.height(10.dp))
            val inputState = remember { mutableStateOf(taskState.maxPoints) }
            CellInput(
                value = inputState.value?.toString().orEmpty(),
                onValueChange = {
                    val points = when {
                        it.isEmpty() -> null
                        else -> it.toIntOrNull() ?: return@CellInput
                    }
                    inputState.value = points
                    taskCreationViewModel.setMaxPoints((points ?: 0))
                },
                label = stringResource(id = R.string.task_stage_max_points_title)
            )
        }

    })