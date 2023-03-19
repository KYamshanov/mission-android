package ru.kyamshanov.mission.project.impl.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import ru.kyamshanov.mission.project.impl.ui.model.ProjectScreenState

@Composable
internal fun ProjectInfoComposable(projectInfo: ProjectScreenState.ProjectInfo) =
    Column {

        TextField(
            value = projectInfo.title.text,
            onValueChange = {},
            enabled = projectInfo.title.editable,
        )

        TextField(
            value = projectInfo.description.text,
            onValueChange = {},
            enabled = projectInfo.description.editable,
        )

    }