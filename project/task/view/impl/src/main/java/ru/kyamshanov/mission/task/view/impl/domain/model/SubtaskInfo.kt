package ru.kyamshanov.mission.task.view.impl.domain.model

import java.util.Date
import ru.kyamshanov.mission.project.common.domain.model.SubtaskId
import ru.kyamshanov.mission.project.common.domain.model.TaskId
import ru.kyamshanov.mission.time.api.MissionDateFormatter

internal data class SubtaskInfo(
    val subtaskId: SubtaskId,
    val taskId: TaskId,
    val title: String,
    val description: String,
    val startAt: Date,
    val endAt: Date,
    val responsible: ResponsibleInfo,
    val dateFormatter: MissionDateFormatter,
    val executionResult: String?,
    val stage: State,
) {

    enum class State {
        CREATED,
        IN_WORK,
        FINISHED
    }
}