package ru.kyamshanov.mission.project.impl.data.api

import ru.kyamshanov.mission.project.impl.data.model.GetTeamRsDto
import ru.kyamshanov.mission.project.impl.data.model.ProjectInfoDto

internal interface ProjectApi {

    suspend fun getProject(projectId: String): ProjectInfoDto

    suspend fun getTeam(projectId: String): GetTeamRsDto

    suspend fun getManagedTeam(projectId: String): GetTeamRsDto
}