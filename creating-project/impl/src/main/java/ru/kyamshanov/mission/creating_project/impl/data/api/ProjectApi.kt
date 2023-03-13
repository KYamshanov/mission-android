package ru.kyamshanov.mission.creating_project.impl.data.api

import ru.kyamshanov.mission.creating_project.impl.data.model.AttachTeamRqDto
import ru.kyamshanov.mission.creating_project.impl.data.model.CreateProjectRqDto
import ru.kyamshanov.mission.creating_project.impl.data.model.CreateProjectRsDto

internal interface ProjectApi {

    suspend fun create(body: CreateProjectRqDto): CreateProjectRsDto

    suspend fun attachTeam(body: AttachTeamRqDto)
}