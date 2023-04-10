package ru.kyamshanov.mission.task.view.impl.ui.composable.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@Composable
internal fun TaskInfoLoading() = Scaffold(
    modifier = Modifier.fillMaxSize().background(MissionTheme.colors.background),
    content = {
        Text(text = "Загрузка...")
    }
)