package ru.kyamshanov.mission.task.view.impl.data.usecase

import javax.inject.Inject
import ru.kyamshanov.mission.project.common.domain.model.TaskId
import ru.kyamshanov.mission.task.view.impl.data.api.ProjectApi
import ru.kyamshanov.mission.task.view.impl.data.mapper.toDomain
import ru.kyamshanov.mission.task.view.impl.domain.model.TaskInfo
import ru.kyamshanov.mission.task.view.impl.domain.usecase.FetchTaskUseCase

internal class FetchTaskUseCaseImpl @Inject constructor(
    private val projectApi: ProjectApi,
) : FetchTaskUseCase {

    override suspend fun invoke(taskId: TaskId): Result<TaskInfo> = runCatching {
        projectApi.getTask(taskId.value).toDomain()
    }
}