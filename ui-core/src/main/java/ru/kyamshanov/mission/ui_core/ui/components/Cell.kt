package ru.kyamshanov.mission.ui_core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@Composable
fun Area(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(MissionTheme.shapes.medium)
            .background(MissionTheme.colors.primary)
            .border(
                color = MissionTheme.colors.darkSecondary,
                width = 2.dp,
                shape = MissionTheme.shapes.medium
            )
            .padding(5.dp),
        content = content,
    )
}

@Preview
@Composable
fun ColumnAreaPreview() {
    MissionTheme {
        Box(modifier = Modifier.background(MissionTheme.colors.background).padding(30.dp)) {
            Area {
                Text(text = "Этап", style = MissionTheme.typography.inputHint)
                Text(text = "Этап", style = MissionTheme.typography.inputHint)
            }
        }
    }
}
