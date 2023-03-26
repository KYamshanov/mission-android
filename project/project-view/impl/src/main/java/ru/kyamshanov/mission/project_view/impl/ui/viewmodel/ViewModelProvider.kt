package ru.kyamshanov.mission.project_view.impl.ui.viewmodel

import dagger.assisted.AssistedFactory

@AssistedFactory
internal interface ViewModelProvider {

    fun createProjectViewModel(projectId: String): ProjectViewModel
}