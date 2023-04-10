package ru.kyamshanov.mission.task.view.impl.ui.viewmodel

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
import ru.kyamshanov.mission.project.common.domain.model.TaskId
import ru.kyamshanov.mission.task.set_points.api.navigation.SetPointsLauncher
import ru.kyamshanov.mission.task.view.impl.domain.model.TaskInfo
import ru.kyamshanov.mission.task.view.impl.domain.model.TaskViewScreenState
import ru.kyamshanov.mission.task.view.impl.domain.usecase.FetchTaskUseCase
import ru.kyamshanov.mission.time.api.MissionDateFormatter
import ru.kyamshanov.mission.time.di.TimeFormat
import ru.kyamshanov.mission.time.di.TimeFormatQualifier

internal class TaskViewModel @AssistedInject constructor(
    @Assisted private val taskId: String,
    private val fetchTaskUseCase: FetchTaskUseCase,
    @TimeFormatQualifier(TimeFormat.DD_MN_YY) private val dateFormatter: MissionDateFormatter,
    private val setPointLauncher: dagger.Lazy<SetPointsLauncher>,
) : ViewModel() {

    private val _screenState = MutableStateFlow(TaskViewScreenState())
    val screenState = _screenState.asStateFlow()

    private var taskInfo: TaskInfo? = null

    init {
        viewModelScope.launch(Dispatchers.Default) {
            fetchTaskUseCase.invoke(TaskId(taskId))
                .onSuccess { taskInfo ->
                    this@TaskViewModel.taskInfo = taskInfo
                    _screenState.update { screenState ->
                        screenState.copy(
                            loading = false,
                            taskInfo = TaskViewScreenState.TaskViewInfo(
                                title = taskInfo.title,
                                description = taskInfo.description,
                                state = taskInfo.state,
                                startAt = taskInfo.startAt,
                                endAt = taskInfo.endAt,
                                dateFormatter = dateFormatter,
                                setPointsButtonVisible = true
                            )
                        )
                    }
                }
                .onFailure {
                    Log.e(TAG, "fetchTaskUseCase exception", it)
                }
        }
    }

    fun openSetPointScreen() {
        taskInfo?.let { info ->
            setPointLauncher.get().launch(TaskId(taskId), info.maxPoints)
        }
    }

    companion object {

        const val TAG = "TaskPresentViewModel"
    }
}