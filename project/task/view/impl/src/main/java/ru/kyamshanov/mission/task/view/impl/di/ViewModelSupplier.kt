package ru.kyamshanov.mission.task.view.impl.di

import dagger.assisted.AssistedFactory
import javax.inject.Inject
import ru.kyamshanov.mission.finding_user.api.navigation.FindingUserLauncher
import ru.kyamshanov.mission.navigation_core.api.Navigator
import ru.kyamshanov.mission.navigation_core.api.ResultProvider
import ru.kyamshanov.mission.task.set_points.api.navigation.SetPointsLauncher
import ru.kyamshanov.mission.task.view.impl.domain.interactor.SubtaskCreationInteractor
import ru.kyamshanov.mission.task.view.impl.domain.interactor.SubtaskInteractor
import ru.kyamshanov.mission.task.view.impl.domain.interactor.TaskInteractor
import ru.kyamshanov.mission.task.view.impl.ui.viewmodel.SubtaskCreationViewModel
import ru.kyamshanov.mission.task.view.impl.ui.viewmodel.SubtaskViewModel
import ru.kyamshanov.mission.task.view.impl.ui.viewmodel.TaskViewModel
import ru.kyamshanov.mission.time.api.MissionDateFormatter
import ru.kyamshanov.mission.time.di.TimeFormat
import ru.kyamshanov.mission.time.di.TimeFormatQualifier

internal interface ViewModelSupplier {

    fun createSubtaskCreationViewModel(task: String): SubtaskCreationViewModel
}

@AssistedFactory
internal interface TaskViewModelFactory {

    fun create(taskId: String): TaskViewModel
}

@AssistedFactory
internal interface SubtaskViewModelFactory {

    fun create(subtask: String): SubtaskViewModel
}

internal class ViewModelSupplierImpl @Inject constructor(
    private val subtaskCreationInteractor: SubtaskCreationInteractor,
    private val findingUserLauncher: FindingUserLauncher,
    private val resultProvider: ResultProvider,
) : ViewModelSupplier {

    override fun createSubtaskCreationViewModel(task: String): SubtaskCreationViewModel =
        SubtaskCreationViewModel(
            task = task,
            subtaskCreationInteractor = subtaskCreationInteractor,
            findingUserLauncher = findingUserLauncher,
            resultProvider = resultProvider
        )
}