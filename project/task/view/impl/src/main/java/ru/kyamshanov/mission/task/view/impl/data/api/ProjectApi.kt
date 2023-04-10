package ru.kyamshanov.mission.task.view.impl.data.api

import ru.kyamshanov.mission.task.view.impl.data.model.GetTaskRsDto

internal interface ProjectApi {

    suspend fun getTask(taskId: String): GetTaskRsDto
}