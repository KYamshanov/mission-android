package ru.kyamshanov.mission.creating_project.impl.domain.models

internal data class CreatingProjectInfo(
    val name: String,
    val description: String,
    val participants: List<Participant>,
)