package ru.kyamshanov.mission.project.impl.ui.model

internal sealed interface ProjectScreenState {

    object Loading : ProjectScreenState

    data class ProjectInfo(
        val title: TextField,
        val description: TextField,
        val participantsCount: Int,
    ) : ProjectScreenState {

        data class TextField(
            val text: String,
            val editable: Boolean,
        )
    }
}
