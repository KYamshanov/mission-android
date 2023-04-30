package ru.kyamshanov.mission.project_view.impl.ui.model

import ru.kyamshanov.mission.project_view.impl.domain.model.ProjectEditingScheme
import ru.kyamshanov.mission.project_view.impl.domain.model.ProjectInfo

internal data class ProjectScreenState(
    val loading: Boolean,
    val projectInfo: ProjectInfo?,
    val editingScheme: ProjectEditingScheme,
    val totalPointsInfo: TotalPointsInfo,
) {

    val participantsCount = projectInfo?.participants?.size ?: 0

    constructor() : this(true, null, ProjectEditingScheme(isEditable = false), TotalPointsInfo())
}
