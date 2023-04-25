package ru.kyamshanov.mission.task.view.impl.domain.model

internal data class TaskEditingScheme(
    val isEditableTitle: Boolean,
    val isEditableDescription: Boolean,
    val isEditableStartAt: Boolean,
    val isEditableEndAt: Boolean,
    val isEditableMaxPoints: Boolean,
    val isEditableSubtasks: Boolean,
    val titleEdited: Boolean,
    val descriptionEdited: Boolean,
    val startAtEdited: Boolean,
    val endAtEdited: Boolean,
    val maxPointsEdited: Boolean,
) {

    constructor(isEditable: Boolean) : this(
        isEditableTitle = isEditable,
        isEditableDescription = isEditable,
        isEditableStartAt = isEditable,
        isEditableEndAt = isEditable,
        isEditableMaxPoints = isEditable,
        isEditableSubtasks = false,
        titleEdited = false,
        descriptionEdited = false,
        startAtEdited = false,
        endAtEdited = false,
        maxPointsEdited = false,
    )

    val hasChanges: Boolean
        get() = titleEdited or descriptionEdited or startAtEdited or endAtEdited or maxPointsEdited
}

internal fun TaskEditingScheme.reset(): TaskEditingScheme = copy(
    titleEdited = false,
    descriptionEdited = false,
    startAtEdited = false,
    endAtEdited = false,
    maxPointsEdited = false,
)