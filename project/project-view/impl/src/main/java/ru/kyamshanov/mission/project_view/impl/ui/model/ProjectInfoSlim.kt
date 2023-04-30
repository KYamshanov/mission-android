package ru.kyamshanov.mission.project_view.impl.ui.model

import ru.kyamshanov.mission.project.common.domain.model.ProjectId
import ru.kyamshanov.mission.project_view.impl.domain.model.ProjectInfo

internal data class ProjectInfoSlim(
    val id: ProjectId,
    val name: String,
)

internal fun ProjectInfo.toSlim() = ProjectInfoSlim(
    id = ProjectId(id),
    name = title
)