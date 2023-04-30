package ru.kyamshanov.mission.creating_project.impl.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.kyamshanov.mission.creating_project.impl.domain.interactor.ProjectInteractor
import ru.kyamshanov.mission.creating_project.impl.domain.models.CreatingProjectInfo
import ru.kyamshanov.mission.creating_project.impl.ui.models.ScreenState
import ru.kyamshanov.mission.navigation_core.api.Navigator

internal class CreatingProjectViewModel @Inject constructor(
    private val projectInteractor: ProjectInteractor,
    private val navigator: Navigator,
) : ViewModel() {

    private val _screenState = MutableStateFlow(ScreenState(hasCreatingError = false))

    val screenState = _screenState.asStateFlow()

    fun createAndOpenProject(projectInfo: CreatingProjectInfo) = viewModelScope.launch {
        projectInteractor.createAndOpenProject(projectInfo)
            .onFailure {
                Log.e(LOG_TAG, "createAndOpenProject error", it)
                _screenState.value = _screenState.value.copy(hasCreatingError = true)
            }
    }

    fun hideCreatingError() {
        _screenState.value = _screenState.value.copy(hasCreatingError = false)
    }

    fun clickOnBack() {
        navigator.exit()
    }

    private companion object {

        const val LOG_TAG = "CreatingProjectViewModel"
    }
}