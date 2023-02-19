package ru.kyamshanov.mission.creating_project.impl.domain.gateway

import ru.kyamshanov.mission.creating_project.impl.domain.models.CreatingProjectInfo
import ru.kyamshanov.mission.creating_project.impl.domain.models.ProjectInfo

internal interface ProjectGateway {

    suspend fun createProject(projectInfo: CreatingProjectInfo): ProjectInfo
}