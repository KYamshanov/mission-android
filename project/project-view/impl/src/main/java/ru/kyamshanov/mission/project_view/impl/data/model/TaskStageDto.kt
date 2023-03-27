package ru.kyamshanov.mission.project_view.impl.data.model

import ru.kyamshanov.mission.project_view.impl.domain.model.TaskStage

internal enum class TaskStageDto {
    WAIT,
    IN_PROGRESS,
    FINISHED
}

internal fun TaskStageDto.toDomain(): TaskStage = when (this) {
    TaskStageDto.WAIT -> TaskStage.WAIT
    TaskStageDto.IN_PROGRESS -> TaskStage.IN_PROGRESS
    TaskStageDto.FINISHED -> TaskStage.FINISHED
}