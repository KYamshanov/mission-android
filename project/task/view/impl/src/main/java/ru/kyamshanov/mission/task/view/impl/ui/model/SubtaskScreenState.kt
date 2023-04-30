package ru.kyamshanov.mission.task.view.impl.ui.model

import ru.kyamshanov.mission.task.view.impl.domain.model.SubtaskEditingScheme
import ru.kyamshanov.mission.task.view.impl.domain.model.SubtaskInfo

internal data class SubtaskScreenState(
    val subtaskInfo: SubtaskInfo?,
    val editingScheme: SubtaskEditingScheme,
    val loading: Boolean,
) {

    constructor() : this(null, SubtaskEditingScheme(isEditable = false), loading = true)
}