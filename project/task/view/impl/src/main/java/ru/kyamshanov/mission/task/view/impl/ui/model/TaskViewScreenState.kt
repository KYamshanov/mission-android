package ru.kyamshanov.mission.task.view.impl.ui.model

import ru.kyamshanov.mission.task.view.impl.domain.model.SubtaskInfo
import ru.kyamshanov.mission.task.view.impl.domain.model.TaskEditingScheme
import ru.kyamshanov.mission.task.view.impl.domain.model.TaskInfo

internal data class TaskViewScreenState(
    val loading: Boolean,
    val taskInfo: TaskInfo?,
    val subtaskLoading: Boolean,
    val subtasks: List<SubtaskInfo>?,
    val setPointsButtonVisible: Boolean,
    val taskEditingScheme: TaskEditingScheme?,
) {

    constructor() : this(
        loading = true,
        taskInfo = null,
        subtaskLoading = true,
        subtasks = null,
        setPointsButtonVisible = false,
        taskEditingScheme = null,
    )
}
