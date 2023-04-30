package ru.kyamshanov.mission.task.view.impl.di

import dagger.assisted.AssistedFactory
import ru.kyamshanov.mission.task.view.impl.domain.model.ProjectInfo
import ru.kyamshanov.mission.task.view.impl.ui.viewmodel.SubtaskCreationViewModel
import ru.kyamshanov.mission.task.view.impl.ui.viewmodel.SubtaskViewModel
import ru.kyamshanov.mission.task.view.impl.ui.viewmodel.TaskViewModel

internal interface ViewModelSupplier {

    fun createSubtaskCreationViewModel(task: String): SubtaskCreationViewModel
}

@AssistedFactory
internal interface TaskViewModelFactory {

    fun create(
        taskId: String,
        projectInfo: ProjectInfo,
    ): TaskViewModel
}

@AssistedFactory
internal interface SubtaskViewModelFactory {

    fun create(subtask: String): SubtaskViewModel
}

@AssistedFactory
internal interface SubtaskCreationViewModelFactory {

    fun create(
        task: String,
        projectInfo: ProjectInfo,
    ): SubtaskCreationViewModel
}