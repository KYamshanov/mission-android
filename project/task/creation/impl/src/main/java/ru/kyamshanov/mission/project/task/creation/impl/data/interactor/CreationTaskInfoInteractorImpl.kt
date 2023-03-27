package ru.kyamshanov.mission.project.task.creation.impl.data.interactor

import java.util.Date
import javax.inject.Inject
import ru.kyamshanov.mission.project.common.domain.model.ProjectId
import ru.kyamshanov.mission.project.task.creation.impl.data.api.ProjectApi
import ru.kyamshanov.mission.project.task.creation.impl.data.model.CreateTaskRqDto
import ru.kyamshanov.mission.project.task.creation.impl.domain.CreationTaskInfoInteractor
import ru.kyamshanov.mission.project.task.creation.impl.domain.model.TaskCreationInfo

internal class CreationTaskInfoInteractorImpl @Inject constructor(
    private val projectApi: ProjectApi,
) : CreationTaskInfoInteractor {

    override var currentTaskCreationInfo = TaskCreationInfo()
        private set

    override fun initialize(projectId: ProjectId): TaskCreationInfo =
        currentTaskCreationInfo.copy(projectId = projectId).also { currentTaskCreationInfo = it }

    override fun setName(title: String): TaskCreationInfo =
        currentTaskCreationInfo.copy(title = title).also { currentTaskCreationInfo = it }

    override fun setDescription(description: String): TaskCreationInfo =
        currentTaskCreationInfo.copy(description = description).also { currentTaskCreationInfo = it }

    override fun setStartAt(startAt: Date): TaskCreationInfo =
        currentTaskCreationInfo.copy(startAt = startAt).also { currentTaskCreationInfo = it }

    override fun setEndAt(endAt: Date): TaskCreationInfo =
        currentTaskCreationInfo.copy(endAt = endAt).also { currentTaskCreationInfo = it }

    override fun setMaxPoints(maxPoints: Int): TaskCreationInfo =
        currentTaskCreationInfo.copy(maxPoints = maxPoints).also { currentTaskCreationInfo = it }

    override suspend fun save(): Result<Unit> = runCatching {
        val projectId = requireNotNull(currentTaskCreationInfo.projectId?.value) { }
        val title = requireNotNull(currentTaskCreationInfo.title.takeIf { it.isNotBlank() }) { }
        val description = requireNotNull(currentTaskCreationInfo.description.takeIf { it.isNotBlank() }) { }
        val startAt = requireNotNull(currentTaskCreationInfo.startAt) { }
        val endAt = requireNotNull(currentTaskCreationInfo.endAt) { }
        val maxPoints = requireNotNull(currentTaskCreationInfo.maxPoints) { }

        projectApi.createTask(
            CreateTaskRqDto(
                projectId = projectId,
                title = title,
                text = description,
                startAt = startAt,
                endAt = endAt,
                maxPoints = maxPoints
            )
        )
    }
}