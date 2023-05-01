package ru.kyamshanov.mission.profile.impl.domain.usecase

import ru.kyamshanov.mission.profile.impl.domain.model.AttachedProjectModel

internal interface GetProjectsUseCase {

    suspend fun invoke(): Result<List<AttachedProjectModel>>
}