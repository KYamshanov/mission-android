package ru.kyamshanov.mission.project_view.impl.domain.model

internal sealed interface ProjectStage {

    object Wait : ProjectStage

    data class InProject(val taskInfo: TaskInfo) : ProjectStage

    object Finished : ProjectStage
}