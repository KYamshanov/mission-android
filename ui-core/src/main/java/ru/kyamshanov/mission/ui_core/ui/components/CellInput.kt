package ru.kyamshanov.mission.ui_core.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
) {
    TextField(
        modifier = modifier
            .border(
                color = MissionTheme.colors.border,
                width = 2.dp,
                shape = MissionTheme.shapes.medium
            )
            .fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        textStyle = MissionTheme.typography.large,
        label = { Text(text = label, style = MissionTheme.typography.inputHint) },
        shape = MissionTheme.shapes.medium,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MissionTheme.colors.primary,
            cursorColor = MissionTheme.colors.darkSecondary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        maxLines = maxLines
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
