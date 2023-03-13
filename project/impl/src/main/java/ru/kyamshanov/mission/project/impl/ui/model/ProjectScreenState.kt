package ru.kyamshanov.mission.project.impl.ui.model

import ru.kyamshanov.mission.project.impl.domain.model.ParticipantFace

internal data class ProjectScreenState(
    val loading: Boolean,
    val title: String?,
    val description: String?,
    val participants: List<ParticipantFace>?,
) {

    constructor() : this(
        loading = true,
        title = null,
        description = null,
        participants = null
    )
}
