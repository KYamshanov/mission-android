package ru.kyamshanov.mission.ui_core.ui.components

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
fun Input(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    maxLines: Int = 1,
    rightIcon: (@Composable () -> Unit)? = null,
) {
    MissionTextField(
        modifier = modifier
            .fillMaxWidth(),
        text = value,
        onValueChange = onValueChange,
        textStyle = MissionTheme.typography.inputText,
        label = { Text(text = label, style = MissionTheme.typography.inputHint) },
        maxLines = maxLines,
        rightIcon = rightIcon
    )
}

@Preview
@Composable
fun SimpleInputPreview() {
    MissionTheme {
        Box(modifier = Modifier.padding(15.dp)) {
            Input(
                value = "Log",
                onValueChange = {},
                label = "Логин"
            )
        }
    }
}
