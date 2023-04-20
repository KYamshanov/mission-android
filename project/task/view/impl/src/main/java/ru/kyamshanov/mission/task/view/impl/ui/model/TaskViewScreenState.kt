package ru.kyamshanov.mission.task.view.impl.ui.model

import java.util.Date
import ru.kyamshanov.mission.task.view.impl.domain.model.TaskInfo
import ru.kyamshanov.mission.time.api.MissionDateFormatter

internal data class TaskViewScreenState(
    val loading: Boolean,
    val taskInfo: TaskViewInfo?,
) {

    constructor() : this(loading = true, taskInfo = null)

    data class TaskViewInfo(
        val title: String,
        val description: String,
        val state: TaskInfo.State,
        val startAt: Date,
        val endAt: Date,
        val dateFormatter: MissionDateFormatter,
        val setPointsButtonVisible: Boolean,
    )
}
