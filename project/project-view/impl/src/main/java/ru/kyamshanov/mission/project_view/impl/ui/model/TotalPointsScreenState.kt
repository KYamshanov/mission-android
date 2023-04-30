package ru.kyamshanov.mission.project_view.impl.ui.model

import ru.kyamshanov.mission.project_view.impl.domain.model.TaskPointsEditingScheme

internal data class TotalPointsScreenState(
    val totalPointsInfo: TotalPointsInfo,
    val editingScheme: TaskPointsEditingScheme,
)