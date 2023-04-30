package ru.kyamshanov.mission.project_view.impl.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.kyamshanov.mission.navigation_core.api.Navigator
import ru.kyamshanov.mission.project.common.domain.model.ProjectId
import ru.kyamshanov.mission.project.common.domain.model.TaskId
import ru.kyamshanov.mission.project.task.creation.api.navigation.ProjectTaskCreationLauncher
import ru.kyamshanov.mission.project_view.impl.domain.interactor.ProjectInteractor
import ru.kyamshanov.mission.project_view.impl.ui.model.ProjectScreenState
import ru.kyamshanov.mission.project_view.impl.ui.model.TotalPointsInfo
import ru.kyamshanov.mission.project_view.impl.ui.model.toSlim
import ru.kyamshanov.mission.project_view.impl.ui.model.toStagePointInfo
import ru.kyamshanov.mission.project_view.impl.ui.screen.ParticipantsListScreen
import ru.kyamshanov.mission.project_view.impl.ui.screen.TotalPointsViewScreen
import ru.kyamshanov.mission.task.view.api.navigation.TaskViewLauncher

internal class ProjectViewModel @AssistedInject constructor(
    @Assisted private val projectId: String,
    private val taskCreationLauncher: ProjectTaskCreationLauncher,
    private val taskViewLauncher: dagger.Lazy<TaskViewLauncher>,
    private val navigator: Navigator,
    private val projectInteractor: ProjectInteractor,
) : ViewModel() {

    private val _screenStateFlow =
        MutableStateFlow(ProjectScreenState())

    val screenStateFlow = _screenStateFlow.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.Default) {
            projectInteractor.editableSchemeStateFlow.collect { editingScheme ->
                _screenStateFlow.update { state -> state.copy(editingScheme = editingScheme) }
            }
        }
    }

    init {
        viewModelScope.launch(Dispatchers.Default) {
            projectInteractor.fetchProjectById(projectId)
                .onSuccess { projectInfo ->
                    _screenStateFlow.update { state ->
                        state.copy(
                            loading = false,
                            projectInfo = projectInfo,
                            totalPointsInfo = TotalPointsInfo(
                                projectInfo.title,
                                projectInfo.tasks.map { it.toStagePointInfo() }
                            ),
                        )
                    }
                }
                .onFailure {
                    Log.e(LOG_TAG, "Get project error", it)
                    //  _screenStateFlow.value = _screenStateFlow.value.copy(loading = false)
                }
        }
    }

    fun createTask() {
        taskCreationLauncher.launch(ProjectId(projectId), screenStateFlow.value.projectInfo?.title.orEmpty())
    }

    fun openTask(taskId: String) {
        screenStateFlow.value.projectInfo?.let { projectInfo ->
            taskViewLauncher.get().launch(
                projectTitle = projectInfo.title,
                taskId = TaskId(taskId),
                projectId = ProjectId(projectInfo.id)
            )
        }
    }

    fun onBack() {
        navigator.exit()
    }

    fun setTitle(title: String) {
        projectInteractor.setTitle(title)
            .onSuccess { projectInfo -> _screenStateFlow.update { it.copy(projectInfo = projectInfo) } }
    }

    fun setDescription(description: String) {
        projectInteractor.setDescription(description)
            .onSuccess { projectInfo -> _screenStateFlow.update { it.copy(projectInfo = projectInfo) } }
    }

    fun saveChanges() {
        viewModelScope.launch {
            projectInteractor.saveChanges()
                .onFailure {
                    Log.e(LOG_TAG, "Save project error", it)
                }
        }
    }

    fun clickOnPoints() {
        navigator.navigateTo(TotalPointsViewScreen(screenStateFlow.value.totalPointsInfo))
    }

    fun clickOnParticipants() {
        screenStateFlow.value.projectInfo?.let { projectInfo ->
            navigator.navigateTo(ParticipantsListScreen(projectInfo.toSlim()))
        }
    }

    private companion object {

        const val LOG_TAG = "ProjectViewModel"
    }
}