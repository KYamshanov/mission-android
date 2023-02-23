package ru.kyamshanov.mission.project.impl.data.model

import ru.kyamshanov.mission.project.impl.domain.model.ProjectInfo

internal fun ProjectInfoRsDto.toDomain() = ProjectInfo(
    id = id, title = title, description = description
)