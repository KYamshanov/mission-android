package ru.kyamshanov.mission.ui_core.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@Composable
fun CellInput(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    maxLines: Int = 1,
    editable: Boolean = true,
    isMasked: Boolean = false,
) = Cell {
    MissionTextField(
        modifier = modifier
            .fillMaxWidth(),
        text = value,
        onValueChange = onValueChange,
        textStyle = MissionTheme.typography.large,
        label = { Text(text = label, style = MissionTheme.typography.inputHint) },
        maxLines = maxLines,
        editable = editable,
        underlined = false,
        isMasked = isMasked,
    )
}

@Preview
@Composable
fun InputPreview() {
    MissionTheme {
        Box(modifier = Modifier.padding(15.dp)) {
            CellInput(
                value = "Log",
                onValueChange = {},
                label = "Логин"
            )
        }
    }
}
