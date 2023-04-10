package ru.kyamshanov.mission.project.task.creation.impl.ui.model

import ru.kyamshanov.mission.project.task.creation.impl.domain.model.TaskCreationInfo
import ru.kyamshanov.mission.time.api.MissionDateFormatter

internal data class TaskCreationScreenState(
    val taskCreationInfo: TaskCreationInfo,
    val dateFormatter: MissionDateFormatter,
)