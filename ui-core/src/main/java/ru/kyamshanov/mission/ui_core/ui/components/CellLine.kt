package ru.kyamshanov.mission.ui_core.ui.components

import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@Composable
fun CellLine(
    modifier: Modifier = Modifier,
) = Divider(modifier = modifier, thickness = 1.dp, color = MissionTheme.colors.darkSecondary)