package ru.kyamshanov.mission.profile.impl.domain.model

internal data class AttachedProjectModel(
    val id: String,
    val title: String,
    val stage: Stage,
    val userRole: Role,
) {

    enum class Stage {
        WAIT,
        IN_PROGRESS,
        FINISHED,
    }

    enum class Role {
        PARTICIPANT,
        LEADER,
        MENTOR
    }
}