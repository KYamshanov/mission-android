package ru.kyamshanov.mission.task.view.impl.data.interactor

import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.kyamshanov.mission.project.common.domain.model.SubtaskId
import ru.kyamshanov.mission.task.view.impl.data.api.ProjectApi
import ru.kyamshanov.mission.task.view.impl.data.mapper.toDomain
import ru.kyamshanov.mission.task.view.impl.data.mapper.toDto
import ru.kyamshanov.mission.task.view.impl.data.model.EditSubtaskRqDto
import ru.kyamshanov.mission.task.view.impl.domain.interactor.SubtaskInteractor
import ru.kyamshanov.mission.task.view.impl.domain.model.ResponsibleInfo
import ru.kyamshanov.mission.task.view.impl.domain.model.SubtaskEditingScheme
import ru.kyamshanov.mission.task.view.impl.domain.model.SubtaskInfo
import ru.kyamshanov.mission.task.view.impl.domain.model.reset
import ru.kyamshanov.mission.time.api.MissionDateFormatter
import ru.kyamshanov.mission.time.di.TimeFormat
import ru.kyamshanov.mission.time.di.TimeFormatQualifier

internal class SubtaskInteractorImpl @Inject constructor(
    private val projectApi: ProjectApi,
    @TimeFormatQualifier(TimeFormat.DD_MN_YY) private val dateFormatter: MissionDateFormatter,
) : SubtaskInteractor {

    private val _editingSchemeStateFlow = MutableStateFlow(SubtaskEditingScheme(isEditable = false))
    private var _subtaskInfoStateFlow: MutableStateFlow<SubtaskInfo>? = null

    override val editingSchemeStateFlow: StateFlow<SubtaskEditingScheme> = _editingSchemeStateFlow.asStateFlow()

    private var currentSubtaskInfo: SubtaskInfo?
        get() = _subtaskInfoStateFlow?.value
        set(value) {
            value?.let { _subtaskInfoStateFlow?.value = it }
        }

    override suspend fun fetchSubtask(subtaskId: SubtaskId): Result<StateFlow<SubtaskInfo>> = runCatching {
        projectApi.getSubtaskBySubtaskId(subtaskId.value)
            .also { dto ->
                _editingSchemeStateFlow.update {
                    SubtaskEditingScheme(isEditable = dto.availableEdit)
                        .copy(isExecutionResultEditable = dto.availableSetResult)
                }
            }
            .let { MutableStateFlow(it.toDomain(dateFormatter)) }
            .also { _subtaskInfoStateFlow = it }
    }

    override fun setTitle(title: String): Result<Unit> = runCatching {
        assert(_editingSchemeStateFlow.value.isEditableTitle) { "Title editing is not available" }
        requireNotNull(currentSubtaskInfo) { "Subtask was not loaded" }
            .copy(title = title)
            .also { task ->
                currentSubtaskInfo = task
                _editingSchemeStateFlow.update { it.copy(titleEdited = true) }
            }
    }

    override fun setDescription(description: String): Result<Unit> = runCatching {
        assert(_editingSchemeStateFlow.value.isEditableDescription) { "Description editing is not available" }
        requireNotNull(currentSubtaskInfo) { "Subtask was not loaded" }
            .copy(description = description)
            .also { task ->
                currentSubtaskInfo = task
                _editingSchemeStateFlow.update { it.copy(descriptionEdited = true) }
            }
    }

    override fun setResponsible(responsible: ResponsibleInfo): Result<Unit> = runCatching {
        assert(_editingSchemeStateFlow.value.isEditableResponsible) { "Responsible editing is not available" }
        requireNotNull(currentSubtaskInfo) { "Subtask was not loaded" }
            .copy(responsible = responsible)
            .also { task ->
                currentSubtaskInfo = task
                _editingSchemeStateFlow.update { it.copy(responsibleEdited = true) }
            }
    }

    override fun setState(state: SubtaskInfo.State): Result<Unit> = runCatching {
        assert(_editingSchemeStateFlow.value.isStateEditable) { "Title editing is not available" }
        requireNotNull(currentSubtaskInfo) { "Subtask was not loaded" }
            .copy(stage = state)
            .also { task ->
                currentSubtaskInfo = task
                _editingSchemeStateFlow.update { it.copy(stateEdited = true) }
            }
    }

    override fun setExecutionResult(result: String): Result<Unit> = runCatching {
        assert(_editingSchemeStateFlow.value.isExecutionResultEditable) { "ExecutionResult editing is not available" }
        requireNotNull(currentSubtaskInfo) { "Subtask was not loaded" }
            .copy(executionResult = result)
            .also { task ->
                currentSubtaskInfo = task
                _editingSchemeStateFlow.update { it.copy(executionResultEdited = true) }
            }
    }

    override suspend fun saveChanges(): Result<Unit> = runCatching {
        val subtask = requireNotNull(currentSubtaskInfo) { "Subtask is not fetched" }
        val editableScheme = _editingSchemeStateFlow.value
        val edited = editableScheme.hasChanges
        val request = EditSubtaskRqDto(
            subtaskId = subtask.subtaskId.value,
            title = subtask.title.takeIf { editableScheme.titleEdited },
            description = subtask.description.takeIf { editableScheme.descriptionEdited },
            responsible = subtask.responsible.id.value.takeIf { editableScheme.responsibleEdited },
            state = subtask.stage.takeIf { editableScheme.stateEdited }?.toDto(),
            executionResult = subtask.executionResult?.takeIf { editableScheme.executionResultEdited },
            startAt = null,
            endAt = null,
        )
        assert(edited) { "Task information was not changed" }
        projectApi.editSubtask(request)
        _editingSchemeStateFlow.value = editableScheme.reset()
    }
}