package ru.kyamshanov.mission.project_view.impl.data.api

import ru.kyamshanov.mission.project_view.impl.data.model.GetTeamRsDto
import ru.kyamshanov.mission.project_view.impl.data.model.ProjectInfoDto

internal interface ProjectApi {

    suspend fun getProject(projectId: String): ProjectInfoDto

    suspend fun getTeam(projectId: String): GetTeamRsDto

    suspend fun getManagedTeam(projectId: String): GetTeamRsDto
}