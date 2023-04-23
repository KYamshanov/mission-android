package ru.kyamshanov.mission.task.view.impl.domain.model

internal data class TaskEditingScheme(
    val isEditableTitle: Boolean,
    val isEditableDescription: Boolean,
    val isEditableStartAt: Boolean,
    val isEditableEndAt: Boolean,
    val isEditableMaxPoints: Boolean,
    var titleEdited: Boolean,
    var descriptionEdited: Boolean,
    var startAtEdited: Boolean,
    var endAtEdited: Boolean,
    var maxPointsEdited: Boolean,
) {

    constructor(isEditable: Boolean) : this(
        isEditableTitle = isEditable,
        isEditableDescription = isEditable,
        isEditableStartAt = isEditable,
        isEditableEndAt = isEditable,
        isEditableMaxPoints = isEditable,
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