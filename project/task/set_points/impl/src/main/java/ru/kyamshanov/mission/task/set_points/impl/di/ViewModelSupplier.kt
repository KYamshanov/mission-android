package ru.kyamshanov.mission.task.set_points.impl.di

import dagger.assisted.AssistedFactory
import ru.kyamshanov.mission.task.set_points.impl.ui.viewmodel.SetPointsViewModel

@AssistedFactory
internal interface SetPointsViewModelFactory {

    fun create(
        taskId: String,
        maxPoints: Int,
    ): SetPointsViewModel
}