package ru.kyamshanov.mission.project.task.creation.impl.ui.viewmodel

import dagger.assisted.AssistedFactory
import ru.kyamshanov.mission.project.common.domain.model.ProjectId

@AssistedFactory
internal interface ViewModelProvider {

    fun createTaskCreationViewModel(projectId: String): TaskCreationViewModel
}