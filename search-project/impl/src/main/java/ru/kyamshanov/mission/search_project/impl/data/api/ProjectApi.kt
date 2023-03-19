package ru.kyamshanov.mission.search_project.impl.data.api

import ru.kyamshanov.mission.search_project.impl.data.model.GetAllProjectsRqDto
import ru.kyamshanov.mission.search_project.impl.data.model.GetAllProjectsRsDto

internal interface ProjectApi {

    suspend fun loadProjects(body: GetAllProjectsRqDto): GetAllProjectsRsDto
}