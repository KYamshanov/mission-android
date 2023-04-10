package ru.kyamshanov.mission.project_view.impl.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.kyamshanov.mission.project.common.domain.model.ProjectId
import ru.kyamshanov.mission.project.common.domain.model.TaskId
import ru.kyamshanov.mission.project.task.creation.api.navigation.ProjectTaskCreationLauncher
import ru.kyamshanov.mission.project_view.impl.domain.usecase.GetProjectUseCase
import ru.kyamshanov.mission.project_view.impl.ui.model.ProjectScreenState
import ru.kyamshanov.mission.session_front.api.SessionInfo
import ru.kyamshanov.mission.session_front.api.model.UserRole
import ru.kyamshanov.mission.task.view.api.navigation.TaskViewLauncher

internal class ProjectViewModel @AssistedInject constructor(
    @Assisted private val projectId: String,
    private val getProjectUseCase: GetProjectUseCase,
    private val sessionInfo: SessionInfo,
    private val taskCreationLauncher: ProjectTaskCreationLauncher,
    private val taskViewLauncher: dagger.Lazy<TaskViewLauncher>,
) : ViewModel() {

    private val _screenStateFlow =
        MutableStateFlow<ProjectScreenState>(ProjectScreenState.Loading)

    val screenStateFlow = _screenStateFlow.asStateFlow()

    fun loadProject() {
        viewModelScope.launch(Dispatchers.Default) {
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
                        participantsCount = projectInfo.participants.size,
                        tasks = projectInfo.tasks,
                        projectStage = projectInfo.projectStage
                    )
                }
                .onFailure {
                    Log.e(LOG_TAG, "Get project error", it)
                    //  _screenStateFlow.value = _screenStateFlow.value.copy(loading = false)
                }
        }
    }

    fun createTask() {
        taskCreationLauncher.launch(ProjectId(projectId))
    }

    fun openTask(taskId: String) {
          taskViewLauncher.get().launch(taskId = TaskId(taskId))
    }

    private companion object {

        const val LOG_TAG = "ProjectViewModel"
    }
}