package ru.kyamshanov.mission.task.view.impl.data.interactor

import java.util.Date
import javax.inject.Inject
import ru.kyamshanov.mission.project.common.domain.model.TaskId
import ru.kyamshanov.mission.session_front.api.SessionInfo
import ru.kyamshanov.mission.session_front.api.model.UserRole
import ru.kyamshanov.mission.task.view.impl.data.api.ProjectApi
import ru.kyamshanov.mission.task.view.impl.data.mapper.toDomain
import ru.kyamshanov.mission.task.view.impl.data.model.EditTaskRqDto
import ru.kyamshanov.mission.task.view.impl.domain.interactor.TaskInteractor
import ru.kyamshanov.mission.task.view.impl.domain.model.TaskEditingScheme
import ru.kyamshanov.mission.task.view.impl.domain.model.TaskInfo
import ru.kyamshanov.mission.task.view.impl.domain.model.reset
import ru.kyamshanov.mission.time.api.MissionDateFormatter
import ru.kyamshanov.mission.time.di.TimeFormat
import ru.kyamshanov.mission.time.di.TimeFormatQualifier

internal class TaskInteractorImpl @Inject constructor(
    private val projectApi: ProjectApi,
    sessionInfo: SessionInfo,
    @TimeFormatQualifier(TimeFormat.DD_MN_YY) private val dateFormatter: MissionDateFormatter,
) : TaskInteractor {

    private var currentTask: TaskInfo? = null

    override var editableScheme: TaskEditingScheme =
        if (sessionInfo.hasRole(UserRole.MANAGER)) TaskEditingScheme(isEditable = true)
        else TaskEditingScheme(isEditable = false)
        private set

    override fun setTitle(title: String): Result<TaskInfo> = runCatching {
        assert(editableScheme.isEditableTitle) { "Title editing is not available" }
        requireNotNull(currentTask) { "Task was not loaded" }
            .copy(title = title)
            .also { task ->
                currentTask = task
                editableScheme.titleEdited = true
            }
    }

    override fun setDescription(description: String): Result<TaskInfo> = runCatching {
        assert(editableScheme.isEditableTitle) { "Description editing is not available" }
        requireNotNull(currentTask) { "Description was not loaded" }
            .copy(description = description)
            .also { task ->
                currentTask = task
                editableScheme.descriptionEdited = true
            }
    }

    override fun setStartAt(startAt: Date): Result<TaskInfo> = runCatching {
        assert(editableScheme.isEditableTitle) { "StartAt editing is not available" }
        requireNotNull(currentTask) { "StartAt was not loaded" }
            .copy(startAt = startAt)
            .also { task ->
                currentTask = task
                editableScheme.startAtEdited = true
            }
    }

    override fun setEndAt(endAt: Date): Result<TaskInfo> = runCatching {
        assert(editableScheme.isEditableTitle) { "EndAt editing is not available" }
        requireNotNull(currentTask) { "EndAt was not loaded" }
            .copy(endAt = endAt)
            .also { task ->
                currentTask = task
                editableScheme.endAtEdited = true
            }
    }

    override fun setMaxPoints(maxPoints: Int): Result<TaskInfo> = runCatching {
        assert(editableScheme.isEditableTitle) { "EndAt editing is not available" }
        requireNotNull(currentTask) { "EndAt was not loaded" }
            .copy(maxPoints = maxPoints)
            .also { task ->
                currentTask = task
                editableScheme.maxPointsEdited = true
            }
    }

    override suspend fun saveChanges(): Result<TaskEditingScheme> = runCatching {
        val task = requireNotNull(currentTask) { "Task was not fetched" }
        var edited = false
        val request = EditTaskRqDto(
            taskId = task.taskId.value,
            title = task.title.takeIf { editableScheme.titleEdited }?.also { edited = true },
            description = task.description.takeIf { editableScheme.descriptionEdited }?.also { edited = true },
            startAt = task.startAt.takeIf { editableScheme.startAtEdited }?.also { edited = true },
            endAt = task.endAt.takeIf { editableScheme.endAtEdited }?.also { edited = true },
            maxPoints = task.maxPoints.takeIf { editableScheme.maxPointsEdited }?.also { edited = true },
            points = null,
        )
        assert(edited) { "Task information was not changed" }
        projectApi.editTask(request)
        editableScheme.reset().also { editableScheme = it }
    }

    override suspend fun fetchTask(taskId: TaskId): Result<TaskInfo> = runCatching {
        projectApi.getTask(taskId.value).toDomain(dateFormatter)
            .also { currentTask = it }
    }
}