package ru.kyamshanov.mission.task.view.impl.domain.model

import ru.kyamshanov.mission.navigation_core.common.SerializableNavigationBoundaryData

internal data class ProjectInfo(
    val projectId: String,
    val projectName: String,
) : SerializableNavigationBoundaryData