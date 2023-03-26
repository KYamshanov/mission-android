package ru.kyamshanov.mission.creating_project.impl.data.model

internal data class AttachTeamRqDto(
    val project: String,
    val participants: Collection<String>
)