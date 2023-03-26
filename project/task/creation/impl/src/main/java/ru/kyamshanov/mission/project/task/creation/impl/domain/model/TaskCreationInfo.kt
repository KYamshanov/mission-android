package ru.kyamshanov.mission.project.task.creation.impl.domain.model

import java.util.Date

internal data class TaskCreationInfo(
    val title: String,
    val description: String,
    val startAt: Date?,
    val endAt: Date?,
    val maxPoints: Int?,
) {

    constructor() : this("", "", null, null, null)


}
