package ru.kyamshanov.mission.project_view.impl.data.usecase

import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kyamshanov.mission.project.common.domain.model.ProjectId
import ru.kyamshanov.mission.project_view.impl.data.api.ProjectApi
import ru.kyamshanov.mission.project_view.impl.data.model.toDomain
import ru.kyamshanov.mission.project_view.impl.domain.model.ParticipantInfo
import ru.kyamshanov.mission.project_view.impl.domain.usecase.FetchTeamUseCase
import ru.kyamshanov.mission.session_front.api.SessionInfo
import ru.kyamshanov.mission.session_front.api.model.UserRole

internal class FetchTeamUseCaseImpl @Inject constructor(
    private val projectApi: ProjectApi,
    private val sessionInfo: SessionInfo,
) : FetchTeamUseCase {

    override suspend fun fetchTeam(projectId: ProjectId): Result<List<ParticipantInfo>> = runCatching {
        withContext(Dispatchers.Default) {
            when {
                sessionInfo.hasRole(UserRole.MANAGER) -> projectApi.getManagedTeam(projectId = projectId.value)
                else -> projectApi.getTeam(projectId = projectId.value)
            }.participants.map { it.toDomain() }
        }
    }
}