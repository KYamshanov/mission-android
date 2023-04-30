package ru.kyamshanov.mission.finding_user.impl.data.api

import ru.kyamshanov.mission.finding_user.impl.data.model.GetTeamRsDto

internal interface ProjectApi {

    suspend fun getTeam(projectId: String): GetTeamRsDto

    suspend fun getManagedTeam(projectId: String): GetTeamRsDto
}