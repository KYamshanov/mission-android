package ru.kyamshanov.mission.project.impl.data.api

import ru.kyamshanov.mission.project.impl.data.model.ProjectInfoRsDto

internal interface ProjectApi {

    suspend fun getProject(projectId: String): ProjectInfoRsDto
}