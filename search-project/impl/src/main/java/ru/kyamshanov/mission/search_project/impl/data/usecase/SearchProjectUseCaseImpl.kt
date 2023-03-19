package ru.kyamshanov.mission.search_project.impl.data.usecase

import javax.inject.Inject
import ru.kyamshanov.mission.search_project.api.domain.SearchProjectUseCase
import ru.kyamshanov.mission.search_project.api.domain.model.PageIndex
import ru.kyamshanov.mission.search_project.api.domain.model.ProjectInfo
import ru.kyamshanov.mission.search_project.impl.data.api.ProjectApi
import ru.kyamshanov.mission.search_project.impl.data.model.GetAllProjectsRqDto
import ru.kyamshanov.mission.search_project.impl.data.model.toDomain

internal class SearchProjectUseCaseImpl @Inject constructor(
    private val projectApi: ProjectApi,
) : SearchProjectUseCase {

    override suspend fun findAll(pageIndex: PageIndex): Result<List<ProjectInfo>> = kotlin.runCatching {
        projectApi.loadProjects(
            GetAllProjectsRqDto(
                pageIndex = GetAllProjectsRqDto.PaginationFilter(pageIndex.page, pageIndex.size),
                filter = GetAllProjectsRqDto.SortingFilter(name = "")
            )
        ).toDomain()
    }

    override suspend fun searchByName(name: String, pageIndex: PageIndex): Result<List<ProjectInfo>> = runCatching {
        projectApi.loadProjects(
            GetAllProjectsRqDto(
                pageIndex = GetAllProjectsRqDto.PaginationFilter(pageIndex.page, pageIndex.size),
                filter = GetAllProjectsRqDto.SortingFilter(name = name)
            )
        ).toDomain()
    }
}