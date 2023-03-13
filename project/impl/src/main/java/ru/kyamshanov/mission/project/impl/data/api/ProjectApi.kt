package ru.kyamshanov.mission.project.impl.data.api

import ru.kyamshanov.mission.project.impl.data.model.FindProjectRsDto

internal interface ProjectApi {

    suspend fun getProject(projectId: String): FindProjectRsDto
}