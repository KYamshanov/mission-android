package ru.kyamshanov.mission.task.view.impl.di

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

    fun createTaskViewModel(taskId: String): TaskViewModel

    fun createSubtaskCreationViewModel(task: String): SubtaskCreationViewModel

    fun createSubtaskViewModel(subtask: String): SubtaskViewModel
}

internal class ViewModelSupplierImpl @Inject constructor(
    @TimeFormatQualifier(TimeFormat.DD_MN_YY) private val dateFormatter: MissionDateFormatter,
    private val setPointsLauncher: dagger.Lazy<SetPointsLauncher>,
    private val subtaskCreationInteractor: SubtaskCreationInteractor,
    private val findingUserLauncher: FindingUserLauncher,
    private val resultProvider: ResultProvider,
    private val subtaskInteractor: SubtaskInteractor,
    private val navigator: Navigator,
    private val taskInteractor: TaskInteractor,
) : ViewModelSupplier {

    override fun createTaskViewModel(taskId: String): TaskViewModel =
        TaskViewModel(
            taskId = taskId,
            dateFormatter = dateFormatter,
            setPointLauncher = setPointsLauncher,
            navigator = navigator,
            subtaskInteractor = subtaskInteractor,
            taskInteractor = taskInteractor,
        )

    override fun createSubtaskCreationViewModel(task: String): SubtaskCreationViewModel =
        SubtaskCreationViewModel(
            task = task,
            subtaskCreationInteractor = subtaskCreationInteractor,
            findingUserLauncher = findingUserLauncher,
            resultProvider = resultProvider
        )

    override fun createSubtaskViewModel(subtask: String): SubtaskViewModel =
        SubtaskViewModel(
            subtask = subtask,
            subtaskInteractor = subtaskInteractor,
        )
}