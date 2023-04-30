package ru.kyamshanov.mission.project_view.impl.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.project_view.api.di.ProjectComponent
import ru.kyamshanov.mission.project_view.impl.di.ModuleComponent
import ru.kyamshanov.mission.project_view.impl.ui.viewmodel.ProjectViewModel
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@Composable
internal fun ProjectComposable(
    projectId: String,
    projectComponent: ModuleComponent = requireNotNull(Di.getInternalComponent<ProjectComponent, ModuleComponent>()),
    projectViewModel: ProjectViewModel = viewModel { projectComponent.projectViewModelFactory.create(projectId = projectId) },
) {

    val screenState by projectViewModel.screenStateFlow.collectAsState()

    Box(
        modifier = Modifier
            .background(MissionTheme.colors.background)
            .fillMaxSize()
    ) {

        if (screenState.loading) ProjectLoadingComposable()
        else screenState.projectInfo?.let { projectInfo ->
            ProjectViewComposable(
                screenState = screenState,
                projectInfo = projectInfo,
                viewModel = projectViewModel,
                taskStagePresentUseCase = projectComponent.taskStagePresentUseCase
            )
        }
    }
}