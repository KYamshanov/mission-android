package ru.kyamshanov.mission.task.view.impl.domain.model

import java.util.Date
import ru.kyamshanov.mission.project.common.domain.model.TaskId
import ru.kyamshanov.mission.time.api.MissionDateFormatter

internal data class TaskInfo(
    val taskId: TaskId,
    val title: String,
    val description: String,
    val state: State,
    val startAt: Date,
    val endAt: Date,
    val maxPoints: Int,
    val dateFormatter: MissionDateFormatter,
) {

    enum class State {
        WAIT,
        IN_PROGRESS,
        FINISHED
    }
}