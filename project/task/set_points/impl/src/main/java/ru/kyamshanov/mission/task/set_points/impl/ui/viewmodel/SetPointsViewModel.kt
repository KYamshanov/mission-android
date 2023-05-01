package ru.kyamshanov.mission.task.set_points.impl.ui.viewmodel

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
import ru.kyamshanov.mission.task.set_points.impl.domain.SetPointsUseCase
import ru.kyamshanov.mission.task.set_points.impl.ui.model.ScreenState

internal class SetPointsViewModel @AssistedInject constructor(
    @Assisted private val taskId: String,
    @Assisted private val maxPoints: Int,
    private val setPointsUseCase: SetPointsUseCase,
    private val navigator: Navigator,
) : ViewModel() {

    private val _screenState = MutableStateFlow(ScreenState(null, maxPoints))
    val screenState = _screenState.asStateFlow()

    fun changePoints(value: Int?) {
        _screenState.update { state ->
            state.copy(points = value)
        }
    }

    fun push() {
        viewModelScope.launch(Dispatchers.Default) {
            _screenState.value.points?.let { points ->
                setPointsUseCase.invoke(taskId = taskId, points)
            }
        }
    }

    fun clickOnBack() {
        navigator.exit()
    }
}