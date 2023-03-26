package ru.kyamshanov.mission.project_view.impl.domain.gateway

import ru.kyamshanov.mission.project_view.impl.domain.model.ProjectInfo

internal interface ProjectGateway {

    suspend fun getProject(projectId: String): ProjectInfo
}