package ru.kyamshanov.mission.search_project.impl.data.model

import ru.kyamshanov.mission.search_project.api.domain.model.ProjectInfo

internal data class GetAllProjectsRqDto(
    val pageIndex: PaginationFilter? = null,
    val filter: SortingFilter,
) {

    data class PaginationFilter(
        val page: Int,
        val size: Int,
    )

    data class SortingFilter(
        val name: String,
    )
}

internal data class GetAllProjectsRsDto(
    val projects: List<ProjectInfoDto>,
)

internal fun GetAllProjectsRsDto.toDomain(): List<ProjectInfo> = projects.map {
    ProjectInfo(it.id, it.title, it.description)
}