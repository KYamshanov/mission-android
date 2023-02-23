package ru.kyamshanov.mission.project.impl.domain.gateway

import ru.kyamshanov.mission.project.impl.domain.model.ProjectInfo

internal interface ProjectGateway {

    suspend fun getProject(projectId: String): ProjectInfo
}