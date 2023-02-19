package ru.kyamshanov.mission.creating_project.impl.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.kyamshanov.mission.creating_project.impl.domain.interactor.ProjectInteractor
import ru.kyamshanov.mission.creating_project.impl.domain.models.CreatingProjectInfo
import ru.kyamshanov.mission.creating_project.impl.ui.models.ScreenState
import javax.inject.Inject

internal class CreatingProjectViewModel @Inject constructor(
    private val projectInteractor: ProjectInteractor
) : ViewModel() {

    private val _screenState = MutableStateFlow(ScreenState(hasCreatingError = false))

    val screenState = _screenState.asStateFlow()

    fun createAndOpenProject(projectInfo: CreatingProjectInfo) = viewModelScope.launch {
        projectInteractor.createAndOpenProject(projectInfo)
            .onFailure {
                _screenState.value = _screenState.value.copy(hasCreatingError = true)
            }
    }
}