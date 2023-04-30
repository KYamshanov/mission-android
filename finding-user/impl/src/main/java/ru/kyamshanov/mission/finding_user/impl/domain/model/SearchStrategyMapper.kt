package ru.kyamshanov.mission.finding_user.impl.domain.model

import ru.kyamshanov.mission.finding_user.api.model.SearchStrategy

internal fun SearchStrategy.toInternal(): InternalSearchStrategy = when (this) {
    SearchStrategy.All -> InternalSearchStrategy.All
    is SearchStrategy.AllByProject -> InternalSearchStrategy.AllByProject(projectId = projectId)
}