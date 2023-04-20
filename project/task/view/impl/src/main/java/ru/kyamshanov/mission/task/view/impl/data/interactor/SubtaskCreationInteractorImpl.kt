package ru.kyamshanov.mission.task.view.impl.data.interactor

import java.util.Date
import javax.inject.Inject
import ru.kyamshanov.mission.project.common.domain.model.SubtaskId
import ru.kyamshanov.mission.project.common.domain.model.TaskId
import ru.kyamshanov.mission.task.view.impl.data.api.ProjectApi
import ru.kyamshanov.mission.task.view.impl.data.model.CreateSubTaskRqDto
import ru.kyamshanov.mission.task.view.impl.domain.interactor.SubtaskCreationInteractor
import ru.kyamshanov.mission.task.view.impl.domain.model.ResponsibleInfo
import ru.kyamshanov.mission.task.view.impl.domain.model.SubtaskCreationInfo
import ru.kyamshanov.mission.time.api.MissionDateFormatter
import ru.kyamshanov.mission.time.di.TimeFormat
import ru.kyamshanov.mission.time.di.TimeFormatQualifier

internal class SubtaskCreationInteractorImpl @Inject constructor(
    @TimeFormatQualifier(TimeFormat.DD_MN_YY) private val dateFormatter: MissionDateFormatter,
    private val projectApi: ProjectApi,
) : SubtaskCreationInteractor {

    private var subtaskCreationInfo: SubtaskCreationInfo? = null

    override fun start(taskId: TaskId): SubtaskCreationInfo =
        SubtaskCreationInfo(taskId, dateFormatter)
            .also { subtaskCreationInfo = it }

    override suspend fun createSubtask(): Result<SubtaskId> = runCatching {
        val creationInfo = requireNotNull(subtaskCreationInfo) { "Subtask info is not initialized" }
        SubtaskId(projectApi.createSubtask(creationInfo.toDto()).id)
    }

    override fun setTitle(title: String): SubtaskCreationInfo =
        requireNotNull(subtaskCreationInfo) { "Subtask info is not initialized" }
            .run { copy(title = title) }
            .also { subtaskCreationInfo = it }

    override fun setDescription(description: String): SubtaskCreationInfo =
        requireNotNull(subtaskCreationInfo) { "Subtask info is not initialized" }
            .run { copy(description = description) }
            .also { subtaskCreationInfo = it }

    override fun setStartAt(startAt: Date): SubtaskCreationInfo =
        requireNotNull(subtaskCreationInfo) { "Subtask info is not initialized" }
            .run { copy(startAt = startAt) }
            .also { subtaskCreationInfo = it }

    override fun setEndAt(endAt: Date): SubtaskCreationInfo =
        requireNotNull(subtaskCreationInfo) { "Subtask info is not initialized" }
            .run { copy(endAt = endAt) }
            .also { subtaskCreationInfo = it }

    override fun setResponsible(responsible: ResponsibleInfo): SubtaskCreationInfo =
        requireNotNull(subtaskCreationInfo) { "Subtask info is not initialized" }
            .also { if (it.responsible == responsible) return it }
            .run { copy(responsible = responsible) }
            .also { subtaskCreationInfo = it }

    private fun SubtaskCreationInfo.toDto(): CreateSubTaskRqDto {
        requireNotNull(startAt) { "Starts time cannot be null" }
        requireNotNull(endAt) { "Ends time cannot be null" }
        requireNotNull(responsible) { "Responsible cannot be null" }
        return CreateSubTaskRqDto(
            taskId = taskId.value,
            title = title,
            description = description,
            startAt = startAt,
            endAt = endAt,
            responsible = responsible.id.value
        )
    }
}