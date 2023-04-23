package ru.kyamshanov.mission.project_view.impl.domain.interactor

import ru.kyamshanov.mission.project.common.domain.model.TaskId
import ru.kyamshanov.mission.project_view.impl.domain.model.TaskPointsInfo
import ru.kyamshanov.mission.project_view.impl.domain.model.TaskPointsEditingScheme

internal interface TaskPointsAnalyticsInteractor {

    val editableScheme: TaskPointsEditingScheme

    fun loadTasks(tasks: List<TaskPointsInfo>)

    fun setPoints(taskId: TaskId, points: Int?): Result<List<TaskPointsInfo>>

    suspend fun saveChanges(): Result<TaskPointsEditingScheme>
}