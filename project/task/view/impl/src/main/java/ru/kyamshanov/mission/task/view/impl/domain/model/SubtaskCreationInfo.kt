package ru.kyamshanov.mission.task.view.impl.domain.model

import java.util.Date
import ru.kyamshanov.mission.project.common.domain.model.TaskId
import ru.kyamshanov.mission.project.common.domain.model.UserId
import ru.kyamshanov.mission.time.api.MissionDateFormatter

internal data class SubtaskCreationInfo(
    val taskId: TaskId,
    val title: String,
    val description: String,
    val startAt: Date?,
    val endAt: Date?,
    val responsible: ResponsibleInfo?,
    val dateFormatter: MissionDateFormatter,
) {

    constructor(taskId: TaskId, missionDateFormatter: MissionDateFormatter) : this(
        taskId = taskId,
        title = "",
        description = "",
        startAt = null,
        endAt = null,
        responsible = null,
        dateFormatter = missionDateFormatter,
    )
}