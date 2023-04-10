package ru.kyamshanov.mission.task.set_points.impl.domain

internal interface SetPointsUseCase {

    suspend operator fun invoke(taskId: String, points: Int): Result<Unit>
}