package ru.kyamshanov.mission.task.view.impl.data.api

import ru.kyamshanov.mission.task.view.impl.data.model.CreateSubTaskRqDto
import ru.kyamshanov.mission.task.view.impl.data.model.CreateSubTaskRsDto
import ru.kyamshanov.mission.task.view.impl.data.model.EditSubtaskRqDto
import ru.kyamshanov.mission.task.view.impl.data.model.EditTaskRqDto
import ru.kyamshanov.mission.task.view.impl.data.model.GetSubTaskRsDto
import ru.kyamshanov.mission.task.view.impl.data.model.GetTaskRsDto
import ru.kyamshanov.mission.task.view.impl.data.model.SetExecutionResultRqDto
import ru.kyamshanov.mission.task.view.impl.data.model.SubtaskDto

internal interface ProjectApi {

    suspend fun getTask(taskId: String): GetTaskRsDto

    suspend fun setExecutionResult(body: SetExecutionResultRqDto)

    suspend fun getSubtaskByTaskId(taskId: String): GetSubTaskRsDto

    suspend fun createSubtask(body: CreateSubTaskRqDto): CreateSubTaskRsDto

    suspend fun getSubtaskBySubtaskId(subtaskId: String): SubtaskDto

    suspend fun editTask(body: EditTaskRqDto)
    suspend fun editSubtask(body: EditSubtaskRqDto)
}