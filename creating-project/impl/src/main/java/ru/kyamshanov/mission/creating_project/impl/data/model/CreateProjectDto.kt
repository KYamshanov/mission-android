package ru.kyamshanov.mission.creating_project.impl.data.model

import ru.kyamshanov.mission.creating_project.impl.domain.models.ProjectInfo

internal data class CreateProjectRqDto(
    val title: String,
    val description: String
)

internal data class CreateProjectRsDto(
    val id: String,
    val title: String,
    val description: String
)

internal fun CreateProjectRsDto.toDomain(): ProjectInfo = ProjectInfo(
    id = id,
    name = title,
    description = description,
)