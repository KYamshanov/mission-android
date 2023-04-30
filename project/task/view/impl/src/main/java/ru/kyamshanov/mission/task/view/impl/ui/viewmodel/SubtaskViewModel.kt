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
import ru.kyamshanov.mission.finding_user.api.navigation.FindingUserLauncher
import ru.kyamshanov.mission.navigation_core.api.Navigator
import ru.kyamshanov.mission.project.common.domain.model.SubtaskId
import ru.kyamshanov.mission.task.view.impl.domain.interactor.SubtaskInteractor
import ru.kyamshanov.mission.task.view.impl.domain.model.ResponsibleInfo
import ru.kyamshanov.mission.task.view.impl.domain.model.SubtaskInfo
import ru.kyamshanov.mission.task.view.impl.ui.model.SubtaskScreenState

internal class SubtaskViewModel @AssistedInject constructor(
    @Assisted private val subtask: String,
    private val subtaskInteractor: SubtaskInteractor,
    private val navigator: Navigator,
    private val findingUserLauncher: FindingUserLauncher
) : ViewModel() {

    private val subtaskId = SubtaskId(subtask)
    private val _screenStateFlow = MutableStateFlow(SubtaskScreenState())
    val screenStateFlow = _screenStateFlow.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.Default) {
            subtaskInteractor.fetchSubtask(subtaskId)
                .onFailure { throwable ->
                    Log.e(TAG, "Fetching error", throwable)
                }
                .getOrNull()?.collect { subtaskInfo ->
                    _screenStateFlow.update { it.copy(loading = false, subtaskInfo = subtaskInfo) }
                }
        }
        viewModelScope.launch(Dispatchers.Default) {
            subtaskInteractor.editingSchemeStateFlow.collect { editingScheme ->
                _screenStateFlow.update { it.copy(editingScheme = editingScheme) }
            }
        }
    }

    fun clickOnBack() {
        navigator.exit()
    }

    fun setTitle(title: String) {
        subtaskInteractor.setTitle(title)
            .onFailure { throwable ->
                Log.e(TAG, "Set title error", throwable)
            }
    }

    fun setDescription(description: String){
        subtaskInteractor.setDescription(description)
            .onFailure { throwable ->
                Log.e(TAG, "setDescription error", throwable)
            }
    }
    fun setResponsible(responsible: ResponsibleInfo){
        subtaskInteractor.setResponsible(responsible)
            .onFailure { throwable ->
                Log.e(TAG, "setResponsible error", throwable)
            }
    }
    fun setState(state: SubtaskInfo.State){
        subtaskInteractor.setState(state)
            .onFailure { throwable ->
                Log.e(TAG, "setState error", throwable)
            }
    }
    fun setExecutionResult(result: String){
        subtaskInteractor.setExecutionResult(result)
            .onFailure { throwable ->
                Log.e(TAG, "setExecutionResult error", throwable)
            }
    }

    fun clickOnSaveChanges(){
        viewModelScope.launch {
            subtaskInteractor.saveChanges()
                .onFailure { throwable ->
                    Log.e(TAG, "saveChanges error", throwable)
                }
        }
    }

    fun clickOnSearchResponsible() {
        findingUserLauncher.launch()
    }

    private companion object {

        const val TAG = "SubtaskViewModel"
    }
}