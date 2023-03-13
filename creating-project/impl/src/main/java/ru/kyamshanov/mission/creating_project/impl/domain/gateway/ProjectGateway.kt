package ru.kyamshanov.mission.creating_project.impl.domain.gateway

import ru.kyamshanov.mission.creating_project.impl.domain.models.CreatedProjectInfo
import ru.kyamshanov.mission.creating_project.impl.domain.models.CreatingProjectInfo
import ru.kyamshanov.mission.creating_project.impl.domain.models.Participant

internal interface ProjectGateway {

    suspend fun createProject(projectInfo: CreatingProjectInfo): CreatedProjectInfo

    suspend fun attachTeam(projectId: String, participants: List<Participant>)
}