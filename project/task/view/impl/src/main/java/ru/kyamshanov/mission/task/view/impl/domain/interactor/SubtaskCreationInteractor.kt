package ru.kyamshanov.mission.task.view.impl.domain.interactor

import java.util.Date
import ru.kyamshanov.mission.project.common.domain.model.SubtaskId
import ru.kyamshanov.mission.project.common.domain.model.TaskId
import ru.kyamshanov.mission.project.common.domain.model.UserId
import ru.kyamshanov.mission.task.view.impl.domain.model.ResponsibleInfo
import ru.kyamshanov.mission.task.view.impl.domain.model.SubtaskCreationInfo

internal interface SubtaskCreationInteractor {

    fun start(taskId: TaskId): SubtaskCreationInfo

    suspend fun createSubtask(): Result<SubtaskId>

    fun setTitle(title: String): SubtaskCreationInfo

    fun setDescription(description: String): SubtaskCreationInfo

    fun setStartAt(startAt: Date): SubtaskCreationInfo

    fun setEndAt(endAt: Date): SubtaskCreationInfo

    fun setResponsible(responsible: ResponsibleInfo): SubtaskCreationInfo
}