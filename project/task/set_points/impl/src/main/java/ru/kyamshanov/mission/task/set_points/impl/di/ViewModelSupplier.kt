package ru.kyamshanov.mission.task.set_points.impl.di

import dagger.assisted.AssistedFactory
import ru.kyamshanov.mission.task.set_points.impl.ui.viewmodel.SetPointsViewModel

@AssistedFactory
internal interface ViewModelSupplier {

    fun getSetPointsViewModel(
        taskId: String,
        maxPoints: Int,
    ): SetPointsViewModel
}