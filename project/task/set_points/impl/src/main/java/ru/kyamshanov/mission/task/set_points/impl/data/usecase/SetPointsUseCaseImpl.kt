package ru.kyamshanov.mission.task.set_points.impl.data.usecase

import javax.inject.Inject
import ru.kyamshanov.mission.task.set_points.impl.data.api.ProjectApi
import ru.kyamshanov.mission.task.set_points.impl.data.model.SetTaskPointsRqDto
import ru.kyamshanov.mission.task.set_points.impl.domain.SetPointsUseCase

internal class SetPointsUseCaseImpl @Inject constructor(
    private val projectApi: ProjectApi,
) : SetPointsUseCase {

    override suspend operator fun invoke(taskId: String, points: Int): Result<Unit> = kotlin.runCatching {
        projectApi.setTaskPoint(SetTaskPointsRqDto(taskId = taskId, points = points))
    }
}