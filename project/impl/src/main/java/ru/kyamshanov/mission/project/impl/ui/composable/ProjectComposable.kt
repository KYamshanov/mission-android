package ru.kyamshanov.mission.project.impl.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.project.api.di.ProjectComponent
import ru.kyamshanov.mission.project.impl.di.ModuleComponent
import ru.kyamshanov.mission.project.impl.ui.model.ProjectScreenState
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

        when (screenState) {
            ProjectScreenState.Loading -> ProjectLoadingComposable()
            is ProjectScreenState.ProjectInfo -> ProjectInfoComposable(projectInfo = screenState as ProjectScreenState.ProjectInfo)
        }
    }
}