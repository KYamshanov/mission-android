package ru.kyamshanov.mission.task.view.impl.ui.composable.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.kyamshanov.mission.task.view.impl.domain.model.SubtaskInfo
import ru.kyamshanov.mission.task.view.impl.domain.model.TaskInfo
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@Composable
internal fun SubtaskStateText(
    modifier: Modifier = Modifier,
    subtaskState: SubtaskInfo.State,
) = when (subtaskState) {
    SubtaskInfo.State.CREATED -> Text(
        modifier = modifier,
        text = "Ожидание",
        style = MissionTheme.typography.green + MissionTheme.typography.medium
    )
    SubtaskInfo.State.IN_WORK -> Text(
        modifier = modifier,
        text = "В работе",
        style = MissionTheme.typography.green + MissionTheme.typography.medium
    )
    SubtaskInfo.State.FINISHED -> Text(
        modifier = modifier,
        text = "Завершена",
        style = MissionTheme.typography.red + MissionTheme.typography.medium
    )
}

@Composable
internal fun TaskStateText(
    state: TaskInfo.State,
) = when (state) {
    TaskInfo.State.WAIT -> Text(
        text = "Ожидание",
        style = MissionTheme.typography.green + MissionTheme.typography.medium
    )
    TaskInfo.State.IN_PROGRESS -> Text(
        text = "В работе",
        style = MissionTheme.typography.green + MissionTheme.typography.medium
    )
    TaskInfo.State.FINISHED -> Text(
        text = "Завершена",
        style = MissionTheme.typography.red + MissionTheme.typography.medium
    )
}