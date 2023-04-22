package ru.kyamshanov.mission.task.view.impl.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import java.util.Date
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.kyamshanov.mission.navigation_core.api.Navigator
import ru.kyamshanov.mission.project.common.domain.model.SubtaskId
import ru.kyamshanov.mission.project.common.domain.model.TaskId
import ru.kyamshanov.mission.task.set_points.api.navigation.SetPointsLauncher
import ru.kyamshanov.mission.task.view.impl.domain.interactor.SubtaskInteractor
import ru.kyamshanov.mission.task.view.impl.domain.interactor.TaskInteractor
import ru.kyamshanov.mission.task.view.impl.domain.model.TaskInfo
import ru.kyamshanov.mission.task.view.impl.ui.model.TaskViewScreenState
import ru.kyamshanov.mission.task.view.impl.ui.screen.SubtaskCreationScreen
import ru.kyamshanov.mission.task.view.impl.ui.screen.SubtaskViewScreen
import ru.kyamshanov.mission.time.api.MissionDateFormatter

internal class TaskViewModel(
    private val taskId: String,
    private val taskInteractor: TaskInteractor,
    private val dateFormatter: MissionDateFormatter,
    private val setPointLauncher: dagger.Lazy<SetPointsLauncher>,
    private val navigator: Navigator,
    private val subtaskInteractor: SubtaskInteractor,
) : ViewModel() {

    private val _screenState = MutableStateFlow(TaskViewScreenState())
    val screenState = _screenState.asStateFlow()

    private var taskInfo: TaskInfo? = null

    init {
        viewModelScope.launch(Dispatchers.Default) {
            fetchTask()
        }
        viewModelScope.launch(Dispatchers.Default) {
            fetchSubtasks()
        }
    }

    fun openSetPointScreen() {
        taskInfo?.let { info ->
            setPointLauncher.get().launch(TaskId(taskId), info.maxPoints)
        }
    }

    fun createSubtask() {
        navigator.navigateTo(SubtaskCreationScreen(taskId = taskId))
    }

    fun openSubtask(subtaskId: SubtaskId) {
        navigator.navigateTo(SubtaskViewScreen(subtaskId.value))
    }

    private suspend fun fetchTask() {
        taskInteractor.fetchTask(TaskId(taskId))
            .onSuccess { taskInfo ->
                this@TaskViewModel.taskInfo = taskInfo
                _screenState.update { screenState ->
                    screenState.copy(
                        loading = false,
                        taskInfo = taskInfo,
                        setPointsButtonVisible = true,
                        taskEditingScheme = taskInteractor.editableScheme,
                        saveChangedButtonVisible = taskInteractor.editableScheme.hasChanges
                    )
                }
            }
            .onFailure {
                Log.e(TAG, "fetchTaskUseCase exception", it)
            }
    }

    private suspend fun fetchSubtasks() {
        subtaskInteractor.loadSubtasks(TaskId(taskId))
            .onSuccess { subtasks ->
                _screenState.update { value ->
                    value.copy(
                        subtaskLoading = false,
                        subtasks = subtasks
                    )
                }
            }
            .onFailure {
                _screenState.update { value -> value.copy(subtaskLoading = false) }
            }
    }

    fun setTitle(title: String) {
        taskInteractor.setTitle(title)
            .onSuccess { taskInfo ->
                _screenState.update { value -> value.copy(taskInfo = taskInfo, saveChangedButtonVisible = true) }
            }
            .onFailure { Log.e(TAG, "exception", it) }
    }

    fun setDescription(description: String) {
        taskInteractor.setDescription(description)
            .onSuccess { taskInfo ->
                _screenState.update { value -> value.copy(taskInfo = taskInfo, saveChangedButtonVisible = true) }
            }
            .onFailure { Log.e(TAG, "exception", it) }
    }

    fun setStartAt(startAt: Date) {
        taskInteractor.setStartAt(startAt)
            .onSuccess { taskInfo ->
                _screenState.update { value -> value.copy(taskInfo = taskInfo, saveChangedButtonVisible = true) }
            }
            .onFailure { Log.e(TAG, "exception", it) }
    }

    fun setEndAt(endAt: Date) {
        taskInteractor.setEndAt(endAt)
            .onSuccess { taskInfo ->
                _screenState.update { value -> value.copy(taskInfo = taskInfo, saveChangedButtonVisible = true) }
            }
            .onFailure { Log.e(TAG, "exception", it) }
    }

    fun setMaxPoints(maxPoints: Int) {
        taskInteractor.setMaxPoints(maxPoints)
            .onSuccess { taskInfo ->
                _screenState.update { value -> value.copy(taskInfo = taskInfo, saveChangedButtonVisible = true) }
            }
            .onFailure { Log.e(TAG, "exception", it) }
    }

    fun saveChanges() {
        viewModelScope.launch {
            taskInteractor.saveChanges()
                .onSuccess {

                }
                .onFailure { Log.e(TAG, "exception", it) }
        }
    }

    companion object {

        const val TAG = "TaskPresentViewModel"
    }
}