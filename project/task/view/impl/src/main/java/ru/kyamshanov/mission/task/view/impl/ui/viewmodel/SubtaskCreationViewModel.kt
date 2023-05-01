package ru.kyamshanov.mission.task.view.impl.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.util.Date
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.kyamshanov.mission.finding_user.api.model.SearchStrategy
import ru.kyamshanov.mission.finding_user.api.navigation.FindingUserLauncher
import ru.kyamshanov.mission.navigation_core.api.Navigator
import ru.kyamshanov.mission.project.common.domain.model.TaskId
import ru.kyamshanov.mission.project.common.domain.model.UserId
import ru.kyamshanov.mission.task.view.impl.domain.interactor.SubtaskCreationInteractor
import ru.kyamshanov.mission.task.view.impl.domain.model.ProjectInfo
import ru.kyamshanov.mission.task.view.impl.domain.model.ResponsibleInfo
import ru.kyamshanov.mission.task.view.impl.ui.model.SubtaskCreationScreenState
import ru.kyamshanov.mission.task.view.impl.ui.screen.SubtaskViewScreen

internal class SubtaskCreationViewModel @AssistedInject constructor(
    @Assisted private val task: String,
    @Assisted private val projectInfo: ProjectInfo,
    private val subtaskCreationInteractor: SubtaskCreationInteractor,
    private val findingUserLauncher: FindingUserLauncher,
    private val navigator: Navigator,
) : ViewModel() {

    private val taskId = TaskId(task)
    private val _screenState: MutableStateFlow<SubtaskCreationScreenState>

    init {
        val subtaskCreationInfo = subtaskCreationInteractor.start(taskId)
        _screenState = MutableStateFlow(SubtaskCreationScreenState(subtaskCreationInfo))
    }

    val screenState = _screenState.asStateFlow()

    fun setTitle(title: String) {
        val subtaskCreationInfo = subtaskCreationInteractor.setTitle(title)
        _screenState.update { it.copy(subtaskInfo = subtaskCreationInfo) }
    }

    fun setDescription(description: String) {
        val subtaskCreationInfo = subtaskCreationInteractor.setDescription(description)
        _screenState.update { it.copy(subtaskInfo = subtaskCreationInfo) }
    }

    fun setStartAt(startAt: Date) {
        val subtaskCreationInfo = subtaskCreationInteractor.setStartAt(startAt)
        _screenState.update { it.copy(subtaskInfo = subtaskCreationInfo) }
    }

    fun setEndAt(endAt: Date) {
        val subtaskCreationInfo = subtaskCreationInteractor.setEndAt(endAt)
        _screenState.update { it.copy(subtaskInfo = subtaskCreationInfo) }
    }

    fun findResponsible() {
        viewModelScope.launch {
            findingUserLauncher.suspendLaunch(SearchStrategy.AllByProject(projectId = projectInfo.projectId))
                ?.also { selectedUser ->
                    setResponsible(ResponsibleInfo(selectedUser.name, UserId(selectedUser.id)))
                }
        }
    }

    private fun setResponsible(responsible: ResponsibleInfo) {
        val subtaskCreationInfo = subtaskCreationInteractor.setResponsible(responsible)
        _screenState.update { it.copy(subtaskInfo = subtaskCreationInfo) }
    }

    fun onCreate() {
        viewModelScope.launch {
            subtaskCreationInteractor.createSubtask()
                .onSuccess { subtaskId ->
                    navigator.replaceTo(SubtaskViewScreen(subtaskId.value))
                }
                .onFailure {
                    Log.e(TAG, "creation error", it)
                }
        }
    }

    fun clickOnBack() {
        navigator.exit()
    }

    private companion object {

        const val TAG = "SubtaskCreationViewModel"
    }
}