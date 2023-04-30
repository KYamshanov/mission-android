package ru.kyamshanov.mission.project_view.impl.di

import kotlinx.coroutines.CoroutineScope
import ru.kyamshanov.mission.project_view.impl.domain.interactor.TeamInteractor

internal interface TeamInteractorFactory {

    fun create(
        projectId: String,
        coroutineScope: CoroutineScope,
    ): TeamInteractor
}