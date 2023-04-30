package ru.kyamshanov.mission.project_view.impl.domain.usecase

import ru.kyamshanov.mission.project.common.domain.model.ProjectId
import ru.kyamshanov.mission.project_view.impl.domain.model.ParticipantInfo

internal interface FetchTeamUseCase {

    suspend fun fetchTeam(projectId : ProjectId) : Result<List<ParticipantInfo>>
}