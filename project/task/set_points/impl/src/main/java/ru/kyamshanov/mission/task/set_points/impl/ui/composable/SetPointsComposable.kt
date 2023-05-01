package ru.kyamshanov.mission.task.set_points.impl.ui.composable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.project.common.domain.model.TaskId
import ru.kyamshanov.mission.task.set_points.api.di.SetPointsComponent
import ru.kyamshanov.mission.task.set_points.impl.di.ModuleComponent
import ru.kyamshanov.mission.task.set_points.impl.ui.viewmodel.SetPointsViewModel
import ru.kyamshanov.mission.ui_core.R
import ru.kyamshanov.mission.ui_core.ui.components.Cell
import ru.kyamshanov.mission.ui_core.ui.components.EditTextField
import ru.kyamshanov.mission.ui_core.ui.components.Icon
import ru.kyamshanov.mission.ui_core.ui.components.MainButton
import ru.kyamshanov.mission.ui_core.ui.components.Surface
import ru.kyamshanov.mission.ui_core.ui.components.TopBar
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@Composable
internal fun SetPointsComposable(
    taskId: TaskId,
    projectName: String,
    taskName: String,
    maxPoints: Int,
    moduleComponent: ModuleComponent = requireNotNull(Di.getInternalComponent<SetPointsComponent, ModuleComponent>()),
    viewModel: SetPointsViewModel = viewModel {
        moduleComponent.setPointsViewModelFactory.create(
            taskId = taskId.value,
            maxPoints
        )
    },
) {
    val screenState by viewModel.screenState.collectAsState()

    Surface(
        topContent = {
            TopBar(title = "Этап - $taskName", subtitle = "$projectName") { viewModel.clickOnBack() }
        },
        bottomContent = {
            MainButton(
                modifier = Modifier.fillMaxWidth(),
                label = "Выставить",
                onClick = { viewModel.push() }
            )
        }
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Cell(Modifier.fillMaxWidth()) {
            Text(
                text = "Выставите сколько баллов заработала команда по результатам этапа",
                style = MissionTheme.typography.title
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        EditTextField(
            text = screenState.points?.toString().orEmpty(),
            label = null,
            suffix = if (screenState.points != null) " баллов" else null,
            onValueChange = { text ->
                if (text.isBlank()) viewModel.changePoints(null)
                else text.toIntOrNull()?.let { viewModel.changePoints(it) }
            },
            rightIcon = {
                Icon(iconResource = R.drawable.close, contentDescription = "Очистить") { viewModel.changePoints(null) }
            },
            editable = true,
        )
        Text(text = "Максимальное количество баллов: ${screenState.maxPoints}")
    }
}