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
import ru.kyamshanov.mission.session_front.api.SessionInfo
import ru.kyamshanov.mission.session_front.api.model.UserRole

internal class ProjectViewModel @Inject constructor(
    private val getProjectUseCase: GetProjectUseCase,
    private val sessionInfo: SessionInfo,
) : ViewModel() {

    private val _screenStateFlow =
        MutableStateFlow<ProjectScreenState>(ProjectScreenState.Loading)

    val screenStateFlow = _screenStateFlow.asStateFlow()

    fun loadProject(projectId: String) {
        viewModelScope.launch {
            val isManager = sessionInfo.hasRole(UserRole.MANAGER)
            _screenStateFlow.value = ProjectScreenState.Loading
            getProjectUseCase.getProjectById(projectId)
                .onSuccess { projectInfo ->
                    _screenStateFlow.value = ProjectScreenState.ProjectInfo(
                        title = ProjectScreenState.ProjectInfo.TextField(
                            text = projectInfo.title,
                            editable = isManager
                        ),
                        description = ProjectScreenState.ProjectInfo.TextField(
                            text = projectInfo.description,
                            editable = isManager
                        ),
                        participantsCount = projectInfo.participants.size
                    )
                }
                .onFailure {
                    Log.e(LOG_TAG, "Get project error", it)
                    //  _screenStateFlow.value = _screenStateFlow.value.copy(loading = false)
                }
        }
    }

    private companion object {

        const val LOG_TAG = "ProjectViewModel"
    }
}