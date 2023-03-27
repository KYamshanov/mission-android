package ru.kyamshanov.mission.project.task.creation.impl.domain.model

import java.util.Date
import ru.kyamshanov.mission.project.common.domain.model.ProjectId

internal data class TaskCreationInfo(
    val projectId: ProjectId?,
    val title: String,
    val description: String,
    val startAt: Date?,
    val endAt: Date?,
    val maxPoints: Int?,
) {

    constructor() : this(null, "", "", null, null, null)
}
