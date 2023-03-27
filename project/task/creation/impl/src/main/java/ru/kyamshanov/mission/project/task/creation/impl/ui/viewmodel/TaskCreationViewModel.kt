package ru.kyamshanov.mission.project.task.creation.impl.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.util.Date
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.kyamshanov.mission.navigation_core.api.Navigator
import ru.kyamshanov.mission.project.common.domain.model.ProjectId
import ru.kyamshanov.mission.project.task.creation.impl.domain.CreationTaskInfoInteractor
import ru.kyamshanov.mission.time.api.DateFormatterProvider
import ru.kyamshanov.mission.project.task.creation.impl.ui.model.TaskCreationScreenState

internal class TaskCreationViewModel @AssistedInject constructor(
    @Assisted private val projectId: String,
    private val taskInfoInteractor: CreationTaskInfoInteractor,
    private val dateFormatterProvider: ru.kyamshanov.mission.time.api.DateFormatterProvider,
    private val navigator: Navigator,
) : ViewModel() {

    private val _screenState: MutableStateFlow<TaskCreationScreenState>

    init {
        val taskCreationInfo = taskInfoInteractor.initialize(ProjectId(projectId))
        _screenState =
            MutableStateFlow(TaskCreationScreenState(taskCreationInfo, dateFormatterProvider.cellDateFormatter()))
    }

    val screenState = _screenState.asStateFlow()

    fun setTitle(title: String) {
        _screenState.value = _screenState.value.copy(
            taskCreationInfo = taskInfoInteractor.setName(title)
        )
    }

    fun setDescription(description: String) {
        _screenState.value = _screenState.value.copy(
            taskCreationInfo = taskInfoInteractor.setDescription(description)
        )
    }

    fun setStartAt(startAt: Date) {
        _screenState.value = _screenState.value.copy(
            taskCreationInfo = taskInfoInteractor.setStartAt(startAt)
        )
    }

    fun setEndAt(endAt: Date) {
        _screenState.value = _screenState.value.copy(
            taskCreationInfo = taskInfoInteractor.setEndAt(endAt)
        )
    }

    fun setMaxPoints(maxPoints: Int) {
        _screenState.value = _screenState.value.copy(
            taskCreationInfo = taskInfoInteractor.setMaxPoints(maxPoints)
        )
    }

    fun save() {
        viewModelScope.launch {
            taskInfoInteractor.save()
                .onSuccess { navigator.exit() }
        }
    }
}