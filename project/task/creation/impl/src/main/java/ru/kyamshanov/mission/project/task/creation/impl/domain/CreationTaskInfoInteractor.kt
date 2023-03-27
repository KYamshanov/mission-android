package ru.kyamshanov.mission.project.task.creation.impl.domain

import java.util.Date
import ru.kyamshanov.mission.project.common.domain.model.ProjectId
import ru.kyamshanov.mission.project.task.creation.impl.domain.model.TaskCreationInfo

internal interface CreationTaskInfoInteractor {

    val currentTaskCreationInfo: TaskCreationInfo

    fun initialize(projectId: ProjectId): TaskCreationInfo

    fun setName(title: String): TaskCreationInfo

    fun setDescription(description: String): TaskCreationInfo

    fun setStartAt(startAt: Date): TaskCreationInfo

    fun setEndAt(endAt: Date): TaskCreationInfo

    fun setMaxPoints(maxPoints: Int): TaskCreationInfo

    suspend fun save(): Result<Unit>
}