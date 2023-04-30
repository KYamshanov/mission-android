package ru.kyamshanov.mission.finding_user.impl.domain.usecase

import ru.kyamshanov.mission.finding_user.impl.domain.model.InternalSearchStrategy

internal interface ObtainUserUseCaseFactory {

    fun create(searchStrategy: InternalSearchStrategy): ObtainUserUseCase
}