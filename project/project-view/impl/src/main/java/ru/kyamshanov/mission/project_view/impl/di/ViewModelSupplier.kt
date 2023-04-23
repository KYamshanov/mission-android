package ru.kyamshanov.mission.project_view.impl.di

import dagger.assisted.AssistedFactory
import ru.kyamshanov.mission.project_view.impl.domain.model.TaskPointsInfo
import ru.kyamshanov.mission.project_view.impl.ui.viewmodel.ProjectViewModel
import ru.kyamshanov.mission.project_view.impl.ui.viewmodel.TotalPointsViewModel

@AssistedFactory
internal interface ProjectViewModelFactory {

    fun create(projectId: String): ProjectViewModel
}

@AssistedFactory
internal interface TotalPointsViewModelFactory {

    fun create(
        projectName: String,
        sourceTaskPoints: List<TaskPointsInfo>,
    ): TotalPointsViewModel
}
