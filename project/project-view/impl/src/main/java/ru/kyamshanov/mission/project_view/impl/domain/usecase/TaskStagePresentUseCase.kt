package ru.kyamshanov.mission.project_view.impl.domain.usecase

import ru.kyamshanov.mission.project_view.impl.domain.model.TaskStage

internal interface TaskStagePresentUseCase {

    operator fun invoke(stage: TaskStage): String
}