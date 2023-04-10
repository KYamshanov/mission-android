package ru.kyamshanov.mission.task.view.impl.domain.usecase

import ru.kyamshanov.mission.project.common.domain.model.TaskId
import ru.kyamshanov.mission.task.view.impl.domain.model.TaskInfo

internal interface FetchTaskUseCase {

    suspend operator fun invoke(taskId: TaskId): Result<TaskInfo>
}