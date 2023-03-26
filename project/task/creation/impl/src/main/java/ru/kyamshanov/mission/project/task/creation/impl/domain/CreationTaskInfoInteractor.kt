package ru.kyamshanov.mission.project.task.creation.impl.domain

import java.util.Date
import javax.inject.Inject
import ru.kyamshanov.mission.project.task.creation.impl.domain.model.TaskCreationInfo

internal interface CreationTaskInfoInteractor {

    val currentTaskCreationInfo: TaskCreationInfo

    fun setName(title: String): TaskCreationInfo

    fun setDescription(description: String): TaskCreationInfo

    fun setStartAt(startAt: Date): TaskCreationInfo

    fun setEndAt(endAt: Date): TaskCreationInfo

    fun setMaxPoints(maxPoints: Int): TaskCreationInfo
}

internal class CreationTaskInfoInteractorImpl @Inject constructor(

) : CreationTaskInfoInteractor {

    override var currentTaskCreationInfo = TaskCreationInfo()
        private set

    override fun setName(title: String): TaskCreationInfo =
        currentTaskCreationInfo.copy(title = title).also { currentTaskCreationInfo = it }

    override fun setDescription(description: String): TaskCreationInfo =
        currentTaskCreationInfo.copy(description = description).also { currentTaskCreationInfo = it }

    override fun setStartAt(startAt: Date): TaskCreationInfo =
        currentTaskCreationInfo.copy(startAt = startAt).also { currentTaskCreationInfo = it }

    override fun setEndAt(endAt: Date): TaskCreationInfo =
        currentTaskCreationInfo.copy(endAt = endAt).also { currentTaskCreationInfo = it }

    override fun setMaxPoints(maxPoints: Int): TaskCreationInfo =
        currentTaskCreationInfo.copy(maxPoints = maxPoints).also { currentTaskCreationInfo = it }
}