package ru.kyamshanov.mission.ui_core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@Composable
fun Loader(
    onDismissRequest: () -> Unit,
) = Dialog(
    onDismissRequest = onDismissRequest,
    properties = DialogProperties(
        dismissOnBackPress = true,
        dismissOnClickOutside = false
    ),
) {
    CircularProgressIndicator(
        modifier = Modifier.drawBehind {
            drawCircle(
                Color.Transparent,
                radius = size.width,
            )
        },
        color = MissionTheme.colors.gray,
    )
}

@Preview
@Composable
fun LoaderPreview() {
    MissionTheme {
        Column(modifier = Modifier.fillMaxSize().padding(15.dp).background(MissionTheme.colors.background)) {

            val visibleState = remember {
                mutableStateOf(true)
            }
            if (visibleState.value) Loader { visibleState.value = false }

        }
    }
}