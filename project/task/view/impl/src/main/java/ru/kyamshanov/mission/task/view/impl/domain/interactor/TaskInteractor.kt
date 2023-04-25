package ru.kyamshanov.mission.task.view.impl.domain.interactor

import java.util.Date
import kotlinx.coroutines.flow.StateFlow
import ru.kyamshanov.mission.project.common.domain.model.TaskId
import ru.kyamshanov.mission.task.view.impl.domain.model.SubtaskInfo
import ru.kyamshanov.mission.task.view.impl.domain.model.TaskEditingScheme
import ru.kyamshanov.mission.task.view.impl.domain.model.TaskInfo

internal interface TaskInteractor {

    val editableSchemeStateFlow: StateFlow<TaskEditingScheme>

    suspend fun fetchTask(taskId: TaskId): Result<TaskInfo>

    fun setTitle(title: String): Result<TaskInfo>

    fun setDescription(description: String): Result<TaskInfo>

    fun setStartAt(startAt: Date): Result<TaskInfo>

    fun setEndAt(endAt: Date): Result<TaskInfo>

    fun setMaxPoints(maxPoints: Int): Result<TaskInfo>

    suspend fun saveChanges(): Result<Unit>

    suspend fun loadSubtasks(taskId: TaskId): Result<List<SubtaskInfo>>
}