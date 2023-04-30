package ru.kyamshanov.mission.task.view.impl.domain.model

internal data class SubtaskEditingScheme(
    val isEditableTitle: Boolean,
    val isEditableDescription: Boolean,
    val isEditableResponsible: Boolean,
    val isEditableState: Boolean,
    val isEditableExecutionResult: Boolean,
    val titleEdited: Boolean,
    val descriptionEdited: Boolean,
    val responsibleEdited: Boolean,
    val stateEdited: Boolean,
    val executionResultEdited: Boolean,
) {

    constructor(isEditable: Boolean) : this(
        isEditableTitle = isEditable,
        isEditableDescription = isEditable,
        isEditableResponsible = isEditable,
        isEditableState = isEditable,
        isEditableExecutionResult = false,
        titleEdited = false,
        descriptionEdited = false,
        responsibleEdited = false,
        stateEdited = false,
        executionResultEdited = false,
    )

    val hasChanges: Boolean
        get() = titleEdited or descriptionEdited or responsibleEdited or stateEdited or executionResultEdited
}

internal fun SubtaskEditingScheme.reset(): SubtaskEditingScheme = copy(
    titleEdited = false,
    descriptionEdited = false,
    responsibleEdited = false,
    stateEdited = false,
    executionResultEdited = false,
)