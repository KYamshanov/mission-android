package ru.kyamshanov.mission.project_view.impl.ui.usecase

import javax.inject.Inject
import ru.kyamshanov.mission.base_core.api.ResourcesProvider
import ru.kyamshanov.mission.project_view.impl.R
import ru.kyamshanov.mission.project_view.impl.domain.model.TaskStage
import ru.kyamshanov.mission.project_view.impl.domain.usecase.TaskStagePresentUseCase
import ru.kyamshanov.mission.time.api.MissionDateFormatter
import ru.kyamshanov.mission.time.di.TimeFormat
import ru.kyamshanov.mission.time.di.TimeFormatQualifier

internal class TaskStagePresentUseCaseImpl @Inject constructor(
    private val resourcesProvider: ResourcesProvider,
    @TimeFormatQualifier(TimeFormat.DD_MN_YY) private val dateFormatter: MissionDateFormatter,
) : TaskStagePresentUseCase {

    override fun invoke(stage: TaskStage): String = when (stage) {
        is TaskStage.Finished -> {
            stage.points?.let { points ->
                resourcesProvider.getString(
                    R.string.pv_finished_with_points,
                    resourcesProvider.getString(R.plurals.pv_point_quantity, points)
                )
            } ?: resourcesProvider.getString(R.string.pv_finished)
        }

        is TaskStage.InProgress -> resourcesProvider.getString(R.string.pv_in_progress_with_time, dateFormatter(stage.finishAt))
        is TaskStage.Wait -> resourcesProvider.getString(R.string.pv_wait, dateFormatter(stage.startAt))
    }
}