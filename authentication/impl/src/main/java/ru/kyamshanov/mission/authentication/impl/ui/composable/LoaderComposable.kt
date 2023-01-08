package ru.kyamshanov.mission.authentication.impl.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@Composable
internal fun LoaderComposable() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MissionTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "У вас задержка.")
    }
}