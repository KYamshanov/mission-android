package ru.kyamshanov.mission.task.view.impl.domain.model

import java.util.Date

internal data class TaskInfo(
    val title: String,
    val description: String,
    val state: State,
    val startAt: Date,
    val endAt: Date,
    val maxPoints: Int,
) {

    enum class State {
        WAIT,
        IN_PROGRESS,
        FINISHED
    }
}