package ru.kyamshanov.mission.project_view.impl.domain.usecase

import javax.inject.Inject
import ru.kyamshanov.mission.project_view.impl.domain.model.ParticipantInfo
import ru.kyamshanov.mission.project_view.impl.domain.model.TeamInfo
import ru.kyamshanov.mission.project_view.impl.utils.getAndRemoveFirst

internal interface FilterParticipantsUseCase {

    fun invoke(participants: List<ParticipantInfo>): TeamInfo
}

internal class FilterParticipantsUseCaseImpl @Inject constructor(): FilterParticipantsUseCase {

    override fun invoke(participants: List<ParticipantInfo>): TeamInfo {
        val leader: ParticipantInfo?
        val mentor: ParticipantInfo?
        val editedParticipants = participants.toMutableList()
            .apply {
                leader = getAndRemoveFirst { it.role == ParticipantInfo.Role.LEADER }
                mentor = getAndRemoveFirst { it.role == ParticipantInfo.Role.MENTOR }
            }
        return TeamInfo(
            mentor = mentor,
            leader = leader,
            participants = editedParticipants,
        )
    }
}