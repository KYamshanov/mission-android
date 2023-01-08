package ru.kyamshanov.mission.ui_core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = MissionColors(
    background = DarkRedPleasant
)

private val LightColorPalette = MissionColors(
    background = RedPleasant
)

private val LocalExtendedColors = staticCompositionLocalOf {
    MissionColors(
        background = Color.Unspecified,
    )
}

object MissionTheme {

    val colors: MissionColors
        @Composable
        get() = LocalExtendedColors.current
}

@Composable
fun MissionTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    CompositionLocalProvider(LocalExtendedColors provides colors) {
        MaterialTheme(
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}