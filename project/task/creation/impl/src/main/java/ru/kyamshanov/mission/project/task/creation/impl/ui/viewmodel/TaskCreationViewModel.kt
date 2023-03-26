package ru.kyamshanov.mission.project.task.creation.impl.ui.viewmodel

import androidx.lifecycle.ViewModel
import java.util.Date
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.kyamshanov.mission.project.task.creation.impl.domain.CreationTaskInfoInteractor
import ru.kyamshanov.mission.project.task.creation.impl.domain.DateFormatterProvider
import ru.kyamshanov.mission.project.task.creation.impl.ui.model.TaskCreationScreenState

internal class TaskCreationViewModel @Inject constructor(
    private val taskInfoInteractor: CreationTaskInfoInteractor,
    private val dateFormatterProvider: DateFormatterProvider,
) : ViewModel() {

    private val _screenState = MutableStateFlow(
        TaskCreationScreenState(taskInfoInteractor.currentTaskCreationInfo, dateFormatterProvider.cellDateFormatter())
    )

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
}