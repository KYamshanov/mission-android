package ru.kyamshanov.mission.task.view.impl.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.kyamshanov.mission.project.common.domain.model.SubtaskId
import ru.kyamshanov.mission.task.view.impl.domain.interactor.SubtaskInteractor
import ru.kyamshanov.mission.task.view.impl.ui.model.SubtaskScreenState

internal class SubtaskViewModel(
    private val subtask: String,
    private val subtaskInteractor: SubtaskInteractor,
) : ViewModel() {

    private val subtaskId = SubtaskId(subtask)
    private val _screenStateFlow = MutableStateFlow(SubtaskScreenState(loading = true, subtaskInfo = null))
    val screenStateFlow = _screenStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            subtaskInteractor.fetchSubtask(subtaskId)
                .onSuccess { subtaskInfo ->
                    _screenStateFlow.update { it.copy(loading = false, subtaskInfo = subtaskInfo) }
                }
                .onFailure { }
        }
    }
}