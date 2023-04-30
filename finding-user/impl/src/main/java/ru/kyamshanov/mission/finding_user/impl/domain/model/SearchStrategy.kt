package ru.kyamshanov.mission.finding_user.impl.domain.model

import ru.kyamshanov.mission.navigation_core.common.SerializableNavigationBoundaryData

internal sealed interface InternalSearchStrategy : SerializableNavigationBoundaryData {

    object All : InternalSearchStrategy

    data class AllByProject(
        val projectId: String,
    ) : InternalSearchStrategy
}