package ru.kyamshanov.mission.project_view.impl.domain.model

import java.util.Date

internal sealed interface TaskStage {

    data class Wait(
        val startAt: Date,
    ) : TaskStage

    data class InProgress(
        val finishAt: Date,
    ) : TaskStage

    data class Finished(
        val points: Int? = null,
    ) : TaskStage
}