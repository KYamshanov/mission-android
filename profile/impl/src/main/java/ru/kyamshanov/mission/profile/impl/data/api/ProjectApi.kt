package ru.kyamshanov.mission.profile.impl.data.api

import ru.kyamshanov.mission.profile.impl.data.model.GetAttachedProjectsRsDto

internal interface ProjectApi {

    suspend fun getAttachedProjects(): GetAttachedProjectsRsDto
}