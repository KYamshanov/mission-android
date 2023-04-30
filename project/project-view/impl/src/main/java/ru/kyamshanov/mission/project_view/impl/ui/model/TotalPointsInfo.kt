package ru.kyamshanov.mission.project_view.impl.ui.model

import ru.kyamshanov.mission.navigation_core.common.SerializableNavigationBoundaryData
import ru.kyamshanov.mission.project_view.impl.domain.model.TaskPointsInfo

internal data class TotalPointsInfo(
    val projectName: String,
    val stagePoints: List<TaskPointsInfo>,
) : SerializableNavigationBoundaryData {

    val totalPoints: Int
        get() = stagePoints.sumOf { it.currentPoints ?: 0 }

    constructor() : this("", emptyList())
}