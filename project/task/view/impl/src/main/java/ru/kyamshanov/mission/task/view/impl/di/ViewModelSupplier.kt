package ru.kyamshanov.mission.task.view.impl.di

import dagger.assisted.AssistedFactory
import ru.kyamshanov.mission.task.view.impl.ui.viewmodel.TaskViewModel

@AssistedFactory
internal interface ViewModelSupplier {

    fun createTaskViewModel(taskId: String): TaskViewModel
}