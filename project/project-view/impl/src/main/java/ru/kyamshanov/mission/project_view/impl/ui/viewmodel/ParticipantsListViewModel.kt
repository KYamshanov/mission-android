package ru.kyamshanov.mission.project_view.impl.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.kyamshanov.mission.finding_user.api.model.SearchStrategy
import ru.kyamshanov.mission.finding_user.api.navigation.FindingUserLauncher
import ru.kyamshanov.mission.navigation_core.api.Navigator
import ru.kyamshanov.mission.project_view.impl.di.TeamInteractorFactory
import ru.kyamshanov.mission.project_view.impl.domain.model.ParticipantInfo
import ru.kyamshanov.mission.project_view.impl.ui.model.ParticipantsListScreenState
import ru.kyamshanov.mission.project_view.impl.ui.model.ProjectInfoSlim

internal class ParticipantsListViewModel @AssistedInject constructor(
    @Assisted private val projectInfoSlim: ProjectInfoSlim,
    private val navigator: Navigator,
    teamInteractorFactory: TeamInteractorFactory,
    private val findingUserLauncher: FindingUserLauncher,
) : ViewModel() {

    private val teamInteractor =
        teamInteractorFactory.create(projectId = projectInfoSlim.id.value, coroutineScope = viewModelScope)

    private val _screenState = MutableStateFlow(ParticipantsListScreenState())
    val screenState = _screenState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.Default) {
            teamInteractor.participantsStateFlow.collect { teamInfo ->
                _screenState.update { it.copy(teamInfo = teamInfo) }
            }
        }
        viewModelScope.launch(Dispatchers.Default) {
            teamInteractor.teamEditingSchemeStateFlow.collect { teamEditingScheme ->
                _screenState.update { it.copy(teamEditingScheme = teamEditingScheme) }
            }
        }
    }

    fun clickOnBack() {
        navigator.exit()
    }

    fun clickOnLeaderToggle(participant: ParticipantInfo) {
        viewModelScope.launch {
            teamInteractor.setLeader(participant)
                .onSuccess { }
        }
    }

    fun clickOnRemove(participant: ParticipantInfo) {
        viewModelScope.launch {
            teamInteractor.removeParticipant(participant)
        }
    }

    fun clickOnAddParticipant() {
        viewModelScope.launch {
            val foundUser = findingUserLauncher.suspendLaunch() ?: return@launch
            teamInteractor.addParticipant(
                ParticipantInfo(foundUser.id, ParticipantInfo.Role.PARTICIPANT)
            )
        }
    }

    fun clickOnChangeMentor() {
        viewModelScope.launch {
            val foundUser =
                findingUserLauncher.suspendLaunch(SearchStrategy.AllByProject(projectId = projectInfoSlim.id.value))
                    ?: return@launch
            teamInteractor.setMentor(
                ParticipantInfo(foundUser.id, ParticipantInfo.Role.MENTOR)
            )
        }
    }
}