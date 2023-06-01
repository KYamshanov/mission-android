package ru.kyamshanov.mission.project_view.impl.data.model

import ru.kyamshanov.mission.project_view.impl.domain.model.ProjectStage

internal enum class ProjectStageDto {
    WAIT,
    IN_PROGRESS,
    FINISHED,
}

internal fun ProjectInfoDto.getStage(): ProjectStage = when (this.stage) {
    ProjectStageDto.WAIT -> ProjectStage.Wait

    ProjectStageDto.IN_PROGRESS -> {
        ProjectStage.InProject(currentTask?.toDomain())
    }

    ProjectStageDto.FINISHED -> ProjectStage.Finished
}