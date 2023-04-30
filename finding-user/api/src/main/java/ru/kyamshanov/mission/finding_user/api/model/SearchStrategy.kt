package ru.kyamshanov.mission.finding_user.api.model

sealed interface SearchStrategy {

    object All : SearchStrategy

    data class AllByProject(
        val projectId: String,
    ) : SearchStrategy
}