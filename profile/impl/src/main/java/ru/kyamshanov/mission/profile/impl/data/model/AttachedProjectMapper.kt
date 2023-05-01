package ru.kyamshanov.mission.profile.impl.data.model

import ru.kyamshanov.mission.profile.impl.domain.model.AttachedProjectModel

internal fun AttachedProjectsInfoDto.toDomain() = AttachedProjectModel(
    id = projectId, title = title, stage = state.toDomain()
)

private fun ProjectStageDto.toDomain(): AttachedProjectModel.Stage = when (this) {
    ProjectStageDto.WAIT -> AttachedProjectModel.Stage.WAIT
    ProjectStageDto.IN_PROGRESS -> AttachedProjectModel.Stage.IN_PROGRESS
    ProjectStageDto.FINISHED -> AttachedProjectModel.Stage.FINISHED
}