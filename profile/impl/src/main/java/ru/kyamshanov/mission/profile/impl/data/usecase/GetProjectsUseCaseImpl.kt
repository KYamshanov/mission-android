package ru.kyamshanov.mission.profile.impl.data.usecase

import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kyamshanov.mission.profile.impl.data.api.ProjectApi
import ru.kyamshanov.mission.profile.impl.data.model.toDomain
import ru.kyamshanov.mission.profile.impl.domain.model.AttachedProjectModel
import ru.kyamshanov.mission.profile.impl.domain.usecase.GetProjectsUseCase

internal class GetProjectsUseCaseImpl @Inject constructor(
    private val projectApi: ProjectApi,
) : GetProjectsUseCase {

    override suspend fun invoke(): Result<List<AttachedProjectModel>> = runCatching {
        withContext(Dispatchers.Default) {
            projectApi.getAttachedProjects().projects.map { it.toDomain() }
        }
    }
}