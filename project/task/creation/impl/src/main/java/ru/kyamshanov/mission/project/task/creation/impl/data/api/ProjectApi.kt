package ru.kyamshanov.mission.project.task.creation.impl.data.api

import ru.kyamshanov.mission.project.task.creation.impl.data.model.CreateTaskRqDto
import ru.kyamshanov.mission.project.task.creation.impl.data.model.CreateTaskRsDto

internal interface ProjectApi {

    suspend fun createTask(body: CreateTaskRqDto): CreateTaskRsDto
}