package ru.kyamshanov.mission.project.impl.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.project.api.di.ProjectComponent
import ru.kyamshanov.mission.project.impl.di.ModuleComponent
import ru.kyamshanov.mission.project.impl.ui.viewmodel.ProjectViewModel

@Composable
internal fun ProjectComposable(
    projectId: String,
    projectComponent: ModuleComponent = requireNotNull(Di.getInternalComponent<ProjectComponent, ModuleComponent>()),
    projectViewModel: ProjectViewModel = viewModel { projectComponent.projectViewModel },
) {

    val screenState = projectViewModel.screenStateFlow.collectAsState()

    LaunchedEffect(projectId) {
        projectViewModel.loadProject(projectId = projectId)
    }

    Column {
        Text(text = screenState.value.loading.toString())
        Text(text = screenState.value.title.orEmpty())
        Text(text = screenState.value.description.orEmpty())
    }
}