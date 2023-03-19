package ru.kyamshanov.mission.search_project.api.domain

import ru.kyamshanov.mission.search_project.api.domain.model.PageIndex
import ru.kyamshanov.mission.search_project.api.domain.model.ProjectInfo

interface SearchProjectUseCase {

    suspend fun findAll(pageIndex: PageIndex): Result<List<ProjectInfo>>

    suspend fun searchByName(name: String, pageIndex: PageIndex): Result<List<ProjectInfo>>
}