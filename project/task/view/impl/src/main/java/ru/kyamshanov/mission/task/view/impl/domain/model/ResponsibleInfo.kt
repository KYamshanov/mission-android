package ru.kyamshanov.mission.task.view.impl.domain.model

import ru.kyamshanov.mission.project.common.domain.model.UserId

internal data class ResponsibleInfo(
    val name: String,
    val id: UserId,
)