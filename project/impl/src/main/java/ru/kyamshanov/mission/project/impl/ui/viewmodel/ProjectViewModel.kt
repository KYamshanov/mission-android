package ru.kyamshanov.mission.project.impl.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.kyamshanov.mission.project.impl.domain.usecase.GetProjectUseCase
import ru.kyamshanov.mission.project.impl.ui.model.ProjectScreenState

internal class ProjectViewModel @Inject constructor(
    private val getProjectUseCase: GetProjectUseCase,
) : ViewModel() {

    private val _screenStateFlow =
        MutableStateFlow(ProjectScreenState(loading = true, title = null, description = null))

    val screenStateFlow = _screenStateFlow.asStateFlow()

    fun loadProject(projectId: String) {
        println("Load project")
        viewModelScope.launch {
            _screenStateFlow.value = _screenStateFlow.value.copy(loading = true)
            getProjectUseCase.getProjectById(projectId)
                .onSuccess { projectInfo ->
                    _screenStateFlow.value = _screenStateFlow.value.copy(
                        title = projectInfo.title,
                        description = projectInfo.description,
                        loading = false
                    )
                }
                .onFailure {
                    Log.e(LOG_TAG, "Get project error", it)
                    _screenStateFlow.value = _screenStateFlow.value.copy(loading = false)
                }
        }
    }

    private companion object {

        const val LOG_TAG = "ProjectViewModel"
    }
}