package ru.kyamshanov.mission.creating_project.impl.domain.gateway

import ru.kyamshanov.mission.creating_project.impl.domain.models.CreatingProjectInfo
import ru.kyamshanov.mission.creating_project.impl.domain.models.CreatedProjectInfo

internal interface ProjectGateway {

    suspend fun createProject(projectInfo: CreatingProjectInfo): CreatedProjectInfo
}