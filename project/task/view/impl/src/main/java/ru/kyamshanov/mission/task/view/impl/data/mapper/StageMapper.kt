package ru.kyamshanov.mission.task.view.impl.data.mapper

import ru.kyamshanov.mission.task.view.impl.data.model.TaskStageDto
import ru.kyamshanov.mission.task.view.impl.domain.model.TaskInfo

internal fun TaskStageDto.toDomain() = when (this) {
    TaskStageDto.WAIT -> TaskInfo.State.WAIT
    TaskStageDto.IN_PROGRESS -> TaskInfo.State.IN_PROGRESS
    TaskStageDto.FINISHED -> TaskInfo.State.FINISHED
}