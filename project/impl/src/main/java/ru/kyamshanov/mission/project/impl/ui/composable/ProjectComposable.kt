package ru.kyamshanov.mission.project.impl.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.project.api.di.ProjectComponent
import ru.kyamshanov.mission.project.impl.di.ModuleComponent
import ru.kyamshanov.mission.project.impl.ui.viewmodel.ProjectViewModel
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@Composable
internal fun ProjectComposable(
    projectId: String,
    projectComponent: ModuleComponent = requireNotNull(Di.getInternalComponent<ProjectComponent, ModuleComponent>()),
    projectViewModel: ProjectViewModel = viewModel { projectComponent.projectViewModel },
) {

    val screenState by projectViewModel.screenStateFlow.collectAsState()

    LaunchedEffect(projectId) {
        projectViewModel.loadProject(projectId = projectId)
    }

    Box(
        modifier = Modifier
            .background(MissionTheme.colors.background)
            .fillMaxSize()
    ) {

        if (screenState.loading) {
            Column {
                Text(text = "Загрузка...")
            }
        } else {
            Column {

                TextField(
                    value = screenState.title.orEmpty(),
                    onValueChange = {},
                    enabled = false,
                    label = { Text(text = "Заголовок") },
                )

                TextField(
                    value = screenState.description.orEmpty(),
                    onValueChange = {},
                    enabled = false,
                    label = { Text(text = "Описание") }
                )
            }
        }

    }
}