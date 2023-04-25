package ru.kyamshanov.mission.task.view.impl.domain.interactor

import kotlinx.coroutines.flow.StateFlow
import ru.kyamshanov.mission.project.common.domain.model.SubtaskId
import ru.kyamshanov.mission.task.view.impl.domain.model.ResponsibleInfo
import ru.kyamshanov.mission.task.view.impl.domain.model.SubtaskEditingScheme
import ru.kyamshanov.mission.task.view.impl.domain.model.SubtaskInfo

internal interface SubtaskInteractor {

    val editingSchemeStateFlow: StateFlow<SubtaskEditingScheme>

    suspend fun fetchSubtask(subtaskId: SubtaskId): Result<StateFlow<SubtaskInfo>>

    fun setTitle(title: String): Result<Unit>
    fun setDescription(description: String): Result<Unit>
    fun setResponsible(responsible: ResponsibleInfo): Result<Unit>
    fun setState(state: SubtaskInfo.State): Result<Unit>
    fun setExecutionResult(result: String): Result<Unit>

    suspend fun saveChanges(): Result<Unit>
}