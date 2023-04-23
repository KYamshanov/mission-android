package ru.kyamshanov.mission.project_view.impl.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.kyamshanov.mission.navigation_core.api.Navigator
import ru.kyamshanov.mission.project.common.domain.model.TaskId
import ru.kyamshanov.mission.project_view.impl.domain.interactor.TaskPointsAnalyticsInteractor
import ru.kyamshanov.mission.project_view.impl.domain.model.TaskPointsEditingScheme
import ru.kyamshanov.mission.project_view.impl.domain.model.TaskPointsInfo
import ru.kyamshanov.mission.project_view.impl.ui.model.TotalPointsInfo
import ru.kyamshanov.mission.project_view.impl.ui.model.TotalPointsScreenState

internal class TotalPointsViewModel @AssistedInject constructor(
    @Assisted private val projectName: String,
    @Assisted private val sourceTaskPoints: List<TaskPointsInfo>,
    private val pointsInteractor: TaskPointsAnalyticsInteractor,
    private val navigator: Navigator,
) : ViewModel() {

    private val _screenState = MutableStateFlow(
        TotalPointsScreenState(TotalPointsInfo(projectName, sourceTaskPoints), TaskPointsEditingScheme())
    )
    val screenState = _screenState.asStateFlow()

    init {
        pointsInteractor.loadTasks(sourceTaskPoints)
        _screenState.update { it.copy(editingScheme = pointsInteractor.editableScheme) }
    }

    fun setPoints(taskId: TaskId, points: Int?) {
        pointsInteractor.setPoints(taskId, points)
            .onSuccess { stagePoints ->
                _screenState.update {
                    it.copy(totalPointsInfo = it.totalPointsInfo.copy(stagePoints = stagePoints))
                }
            }
    }

    fun saveChanges() {
        viewModelScope.launch {
            pointsInteractor.saveChanges()
                .onSuccess { editingScheme ->
                    _screenState.update { it.copy(editingScheme = editingScheme) }
                }
                .onFailure {
                    println("Пошёл на ***")
                }
        }
    }

    fun clickOnBack() {
        navigator.exit()
    }
}