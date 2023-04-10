package ru.kyamshanov.mission.task.view.impl.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.kyamshanov.mission.task.view.impl.R
import ru.kyamshanov.mission.task.view.impl.domain.model.TaskViewScreenState
import ru.kyamshanov.mission.task.view.impl.ui.viewmodel.TaskViewModel
import ru.kyamshanov.mission.ui_core.ui.components.Cell
import ru.kyamshanov.mission.ui_core.ui.components.DateField
import ru.kyamshanov.mission.ui_core.ui.components.SecondaryButton
import ru.kyamshanov.mission.ui_core.ui.components.TextFieldCompose
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@Composable
internal fun TaskInfoSurface(
    info: TaskViewScreenState.TaskViewInfo,
    viewModel: TaskViewModel,
) = Scaffold(
    modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
    backgroundColor = MissionTheme.colors.background,
    bottomBar = {
        if (info.setPointsButtonVisible) {
            SecondaryButton(label = stringResource(id = R.string.tv_set_points_title)) {
                viewModel.openSetPointScreen()
            }
        }
    },
    content = {
        TaskInfoCell(info)
    }
)

@Composable
private fun TaskInfoCell(
    info: TaskViewScreenState.TaskViewInfo,
) = Column {

    TextFieldCompose(
        text = info.title,
        label = stringResource(id = R.string.tv_title),
        editable = false,
        onValueChange = {}
    )

    TextFieldCompose(
        text = info.description,
        label = stringResource(id = R.string.tv_description),
        editable = false,
        onValueChange = {}
    )

    Cell {
        Text(text = stringResource(id = R.string.tv_state, info.state))
        DateField(
            value = info.startAt,
            onValueChange = {},
            label = stringResource(id = R.string.tv_start_at),
            missionDateFormatter = info.dateFormatter,
        )
        DateField(
            value = info.endAt,
            onValueChange = {},
            label = stringResource(id = R.string.tv_end_at),
            missionDateFormatter = info.dateFormatter,
        )
    }
}