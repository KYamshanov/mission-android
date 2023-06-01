package ru.kyamshanov.mission.profile.impl.data.model

import ru.kyamshanov.mission.profile.impl.domain.model.AttachedProjectModel

internal fun AttachedProjectsInfoDto.toDomain() = AttachedProjectModel(
    id = projectId, title = title, stage = state.toDomain(), userRole = useRole.toDomain()
)

private fun ProjectStageDto.toDomain(): AttachedProjectModel.Stage = when (this) {
    ProjectStageDto.WAIT -> AttachedProjectModel.Stage.WAIT
    ProjectStageDto.IN_PROGRESS -> AttachedProjectModel.Stage.IN_PROGRESS
    ProjectStageDto.FINISHED -> AttachedProjectModel.Stage.FINISHED
}

private fun ParticipantDto.Role.toDomain() = when (this) {
    ParticipantDto.Role.PARTICIPANT -> AttachedProjectModel.Role.PARTICIPANT
    ParticipantDto.Role.LEADER -> AttachedProjectModel.Role.LEADER
    ParticipantDto.Role.MENTOR -> AttachedProjectModel.Role.MENTOR
}