package ru.kyamshanov.mission.creating_project.impl.data.model

import ru.kyamshanov.mission.creating_project.impl.domain.models.CreatedProjectInfo

internal data class CreateProjectRqDto(
    val title: String,
    val description: String,
)

internal data class CreateProjectRsDto(
    val id: String,
)

internal fun CreateProjectRsDto.toDomain(): CreatedProjectInfo = CreatedProjectInfo(
    id = id,
)