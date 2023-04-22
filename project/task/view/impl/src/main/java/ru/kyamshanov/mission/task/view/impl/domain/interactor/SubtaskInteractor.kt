package ru.kyamshanov.mission.task.view.impl.domain.interactor

import ru.kyamshanov.mission.project.common.domain.model.SubtaskId
import ru.kyamshanov.mission.project.common.domain.model.TaskId
import ru.kyamshanov.mission.task.view.impl.domain.model.SubtaskInfo

internal interface SubtaskInteractor {

    suspend fun  loadSubtasks(taskId : TaskId) : Result<List<SubtaskInfo>>

    suspend fun fetchSubtask(subtaskId: SubtaskId): Result<SubtaskInfo>
}