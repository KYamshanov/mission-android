package ru.kyamshanov.mission.ui_core.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@Composable
fun MainButton(
    modifier: Modifier = Modifier,
    label: String,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        content = { Text(text = label, style = MissionTheme.typography.mainButtonStyle) },
        colors = buttonColors(
            backgroundColor = MissionTheme.colors.secondary
        ),
        shape = MissionTheme.shapes.mediumLarge
    )
}

@Preview
@Composable
fun MainButtonPreview() {
    MissionTheme {
        Box(modifier = Modifier.padding(15.dp)) {
            MainButton(
                label = "Войти",
                onClick = {}
            )
        }
    }
}
