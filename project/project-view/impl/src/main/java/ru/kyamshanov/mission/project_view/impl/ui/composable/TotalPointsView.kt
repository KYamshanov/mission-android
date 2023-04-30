package ru.kyamshanov.mission.project_view.impl.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.project_view.api.di.ProjectComponent
import ru.kyamshanov.mission.project_view.impl.di.ModuleComponent
import ru.kyamshanov.mission.project_view.impl.ui.model.TotalPointsInfo
import ru.kyamshanov.mission.project_view.impl.ui.viewmodel.TotalPointsViewModel
import ru.kyamshanov.mission.ui_core.ui.components.Cell
import ru.kyamshanov.mission.ui_core.ui.components.MainButton
import ru.kyamshanov.mission.ui_core.ui.components.Surface
import ru.kyamshanov.mission.ui_core.ui.components.TextFieldCompose
import ru.kyamshanov.mission.ui_core.ui.components.TopBar
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@Composable
internal fun TotalPointsView(
    screenInfo: TotalPointsInfo,
    component: ModuleComponent = requireNotNull(Di.getInternalComponent<ProjectComponent, ModuleComponent>()),
    viewModel: TotalPointsViewModel = androidx.lifecycle.viewmodel.compose.viewModel {
        component.totalPointsViewModelFactory.create(
            projectName = screenInfo.projectName,
            sourceTaskPoints = screenInfo.stagePoints
        )
    },
) {

    val screenState by viewModel.screenState.collectAsState()

    Surface(
        topContent = {
            TopBar(
                title = screenState.totalPointsInfo.projectName,
                navigationListener = viewModel::clickOnBack
            )
        },
        bottomContent = {
            if (screenState.editingScheme.hasChanges) {
                Box(modifier = Modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
                    MainButton(
                        label = "Сохранить изменения",
                        onClick = viewModel::saveChanges
                    )
                }
            }
        }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            screenState.totalPointsInfo.stagePoints.forEach { taskInfo ->

                Spacer(modifier = Modifier.height(10.dp))
                Cell(modifier = Modifier.height(60.dp).padding(5.dp)) {
                    TextFieldCompose(
                        modifier = Modifier.fillMaxWidth(),
                        text = taskInfo.currentPoints?.toString().orEmpty(),
                        editable = screenState.editingScheme.isEditablePoints,
                        label = "За ${taskInfo.taskTitle}",
                        onValueChange = {
                            val points: Int? = when {
                                it.isEmpty() -> null
                                else -> it.toIntOrNull() ?: return@TextFieldCompose
                            }
                            viewModel.setPoints(taskInfo.taskId, points)
                        },
                        suffix = " / ${taskInfo.maxPoints} баллов",
                        underlined = false
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Всего ${screenState.totalPointsInfo.totalPoints} баллов",
                style = MissionTheme.typography.inputText
            )
        }
    }
}