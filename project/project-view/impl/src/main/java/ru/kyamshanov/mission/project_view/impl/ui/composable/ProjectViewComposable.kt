package ru.kyamshanov.mission.project_view.impl.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import ru.kyamshanov.mission.project_view.impl.ui.model.ProjectScreenState
import ru.kyamshanov.mission.project_view.impl.ui.viewmodel.ProjectViewModel

@Composable
internal fun ProjectViewComposable(
    projectInfo: ProjectScreenState.ProjectInfo,
    viewModel: ProjectViewModel,
) =
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

        Button(onClick = { viewModel.createTask() }) {
            Text(text = "Add stage")
        }

    }