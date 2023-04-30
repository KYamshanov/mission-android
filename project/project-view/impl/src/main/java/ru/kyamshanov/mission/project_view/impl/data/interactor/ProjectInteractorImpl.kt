package ru.kyamshanov.mission.project_view.impl.data.interactor

import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.kyamshanov.mission.project_view.impl.data.api.ProjectApi
import ru.kyamshanov.mission.project_view.impl.data.model.EditProjectRqDto
import ru.kyamshanov.mission.project_view.impl.domain.interactor.ProjectInteractor
import ru.kyamshanov.mission.project_view.impl.domain.model.ProjectEditingScheme
import ru.kyamshanov.mission.project_view.impl.domain.model.ProjectInfo
import ru.kyamshanov.mission.project_view.impl.domain.model.reset
import ru.kyamshanov.mission.project_view.impl.domain.usecase.GetProjectUseCase
import ru.kyamshanov.mission.session_front.api.SessionInfo
import ru.kyamshanov.mission.session_front.api.model.UserRole

internal class ProjectInteractorImpl @Inject constructor(
    private val getProjectUseCase: GetProjectUseCase,
    private val projectApi: ProjectApi,
    sessionInfo: SessionInfo,
) : ProjectInteractor {

    private val _editableSchemeStateFlow = MutableStateFlow(
        if (sessionInfo.hasRole(UserRole.MANAGER)) ProjectEditingScheme(isEditable = true)
        else ProjectEditingScheme(isEditable = false)
    )
    override val editableSchemeStateFlow: StateFlow<ProjectEditingScheme> = _editableSchemeStateFlow.asStateFlow()

    private var currentProject: ProjectInfo? = null

    override suspend fun fetchProjectById(projectId: String): Result<ProjectInfo> =
        getProjectUseCase.getProjectById(projectId)
            .onSuccess { currentProject = it }

    override fun setTitle(title: String): Result<ProjectInfo> = runCatching {
        assert(editableSchemeStateFlow.value.isEditableTitle) { "Title editing is not available" }
        requireNotNull(currentProject) { "Project was not loaded" }
            .copy(title = title)
            .also { project ->
                currentProject = project
                _editableSchemeStateFlow.update { it.copy(titleEdited = true) }
            }
    }

    override fun setDescription(description: String): Result<ProjectInfo> = runCatching {
        assert(editableSchemeStateFlow.value.isEditableDescription) { "Description editing is not available" }
        requireNotNull(currentProject) { "Project was not loaded" }
            .copy(description = description)
            .also { project ->
                currentProject = project
                _editableSchemeStateFlow.update { it.copy(descriptionEdited = true) }
            }
    }

    override suspend fun saveChanges(): Result<Unit> = runCatching {
        val task = requireNotNull(currentProject) { "Project was not fetched" }
        val editableScheme = _editableSchemeStateFlow.value
        val edited = editableScheme.hasChanges
        val request = EditProjectRqDto(
            projectId = task.id,
            title = task.title.takeIf { editableScheme.titleEdited },
            description = task.description.takeIf { editableScheme.descriptionEdited },
        )
        assert(edited) { "Project information has no changes" }
        projectApi.editProject(request)
        editableScheme.reset().also { _editableSchemeStateFlow.value = it }
    }
}