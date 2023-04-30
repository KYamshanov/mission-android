package ru.kyamshanov.mission.project_view.impl.data.interactor

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.kyamshanov.mission.project.common.domain.model.ProjectId
import ru.kyamshanov.mission.project_view.impl.data.api.ProjectApi
import ru.kyamshanov.mission.project_view.impl.data.model.AddParticipantRqDto
import ru.kyamshanov.mission.project_view.impl.data.model.ParticipantDto
import ru.kyamshanov.mission.project_view.impl.data.model.RemoveParticipantRqDto
import ru.kyamshanov.mission.project_view.impl.data.model.SetRoleRqDto
import ru.kyamshanov.mission.project_view.impl.data.model.toDto
import ru.kyamshanov.mission.project_view.impl.di.TeamInteractorFactory
import ru.kyamshanov.mission.project_view.impl.domain.interactor.TeamInteractor
import ru.kyamshanov.mission.project_view.impl.domain.model.ParticipantInfo
import ru.kyamshanov.mission.project_view.impl.domain.model.TeamEditingScheme
import ru.kyamshanov.mission.project_view.impl.domain.model.TeamInfo
import ru.kyamshanov.mission.project_view.impl.domain.usecase.FetchTeamUseCase
import ru.kyamshanov.mission.project_view.impl.domain.usecase.FilterParticipantsUseCase
import ru.kyamshanov.mission.session_front.api.SessionInfo
import ru.kyamshanov.mission.session_front.api.model.UserRole

internal class TeamInteractorImpl @AssistedInject constructor(
    @Assisted private val projectId: String,
    @Assisted private val coroutineScope: CoroutineScope,
    private val projectApi: ProjectApi,
    private val fetchTeamUseCase: FetchTeamUseCase,
    private val filterParticipantsUseCase: FilterParticipantsUseCase,
    sessionInfo: SessionInfo,
) : TeamInteractor {

    private val _participantsStateFlow = MutableStateFlow(TeamInfo())
    override val participantsStateFlow = _participantsStateFlow.asStateFlow()

    private val _teamEditingSchemeStateFlow = MutableStateFlow(
        if (sessionInfo.hasRole(UserRole.MANAGER)) TeamEditingScheme(isEditable = true)
        else TeamEditingScheme(isEditable = false)
    )
    override val teamEditingSchemeStateFlow: StateFlow<TeamEditingScheme> = _teamEditingSchemeStateFlow.asStateFlow()

    init {
        coroutineScope.launch(Dispatchers.IO) {
            fetchTeamUseCase.fetchTeam(ProjectId(projectId))
                .onSuccess { publishParticipants(it) }
        }
    }

    override suspend fun setLeader(participant: ParticipantInfo): Result<Unit> = runCatching {
        projectApi.setRole(
            SetRoleRqDto(
                projectId = projectId,
                userId = participant.userId,
                role = ParticipantDto.Role.LEADER
            )
        )
        publishParticipants(fetchTeamUseCase.fetchTeam(ProjectId(projectId)).getOrThrow())
    }

    override suspend fun setMentor(participant: ParticipantInfo): Result<Unit> = runCatching {
        projectApi.setRole(
            SetRoleRqDto(
                projectId = projectId,
                userId = participant.userId,
                role = ParticipantDto.Role.MENTOR
            )
        )
        publishParticipants(fetchTeamUseCase.fetchTeam(ProjectId(projectId)).getOrThrow())
    }

    override suspend fun removeParticipant(participant: ParticipantInfo): Result<Unit> = runCatching {
        projectApi.removeParticipant(
            RemoveParticipantRqDto(
                projectId = projectId,
                userId = participant.userId,
            )
        )
        publishParticipants(fetchTeamUseCase.fetchTeam(ProjectId(projectId)).getOrThrow())
    }

    override suspend fun addParticipant(participant: ParticipantInfo): Result<Unit> = runCatching {
        projectApi.addParticipant(
            AddParticipantRqDto(
                projectId = projectId,
                userId = participant.userId,
                role = participant.role.toDto()
            )
        )
        publishParticipants(fetchTeamUseCase.fetchTeam(ProjectId(projectId)).getOrThrow())
    }

    private suspend fun publishParticipants(participants: List<ParticipantInfo>) {
        _participantsStateFlow.emit(filterParticipantsUseCase.invoke(participants))
    }

    @AssistedFactory
    internal interface TeamInteractorAssistedFactory : TeamInteractorFactory {

        override fun create(
            projectId: String,
            coroutineScope: CoroutineScope,
        ): TeamInteractorImpl
    }
}