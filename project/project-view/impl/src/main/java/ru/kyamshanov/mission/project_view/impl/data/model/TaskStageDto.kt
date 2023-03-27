package ru.kyamshanov.mission.project_view.impl.data.model

import java.util.Date
import ru.kyamshanov.mission.project_view.impl.domain.model.TaskStage

internal enum class TaskStageDto {
    WAIT,
    IN_PROGRESS,
    FINISHED
}

internal fun TaskStageDto.toDomain(startAt: Date, endAt: Date, points: Int?): TaskStage = when (this) {
    TaskStageDto.WAIT -> TaskStage.Wait(startAt)
    TaskStageDto.IN_PROGRESS -> TaskStage.InProgress(endAt)
    TaskStageDto.FINISHED -> TaskStage.Finished(points)
}