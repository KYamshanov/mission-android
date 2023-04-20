package ru.kyamshanov.mission.task.view.impl.domain.interactor

import ru.kyamshanov.mission.project.common.domain.model.SubtaskId
import ru.kyamshanov.mission.task.view.impl.domain.model.SubtaskInfo

internal interface SubtaskInteractor {

    suspend fun fetchSubtask(subtaskId: SubtaskId): Result<SubtaskInfo>
}