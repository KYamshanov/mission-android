package ru.kyamshanov.mission.profile.impl.data.model

internal data class AttachedProjectsInfoDto(
    val projectId: String,
    val title: String,
    val state: ProjectStageDto,
)
