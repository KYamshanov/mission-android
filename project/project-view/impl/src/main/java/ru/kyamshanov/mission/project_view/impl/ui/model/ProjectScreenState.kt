package ru.kyamshanov.mission.project_view.impl.ui.model

import ru.kyamshanov.mission.project_view.impl.domain.model.SlimTaskInfo

internal sealed interface ProjectScreenState {

    object Loading : ProjectScreenState

    data class ProjectInfo(
        val title: TextField,
        val description: TextField,
        val participantsCount: Int,
        val tasks: List<SlimTaskInfo>,
    ) : ProjectScreenState {

        data class TextField(
            val text: String,
            val editable: Boolean,
        )
    }
}
