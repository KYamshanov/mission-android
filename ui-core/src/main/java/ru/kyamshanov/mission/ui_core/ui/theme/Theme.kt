package ru.kyamshanov.mission.ui_core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

private val LocalExtendedColors = staticCompositionLocalOf {
    MissionColors(
        background = Color.Unspecified,
        mainButton = Color.Unspecified,
        baseButtonText = Color.Unspecified,
        input = Color.Unspecified,
        baseText = Color.Unspecified,
        secondText = Color.Unspecified,
        secondButton = Color.Unspecified,
        secondButtonText = Color.Unspecified,
        success = Color.Unspecified,
        wrong = Color.Unspecified,
        focusedBorder = Color.Unspecified,
        border = Color.Unspecified,
    )
}

private val LocalExtendedTypography = staticCompositionLocalOf {
    MissionTypography(
        large = TextStyle.Default,
        small = TextStyle.Default,
        mainButton = TextStyle.Default,
    )
}

object MissionTheme {

    val colors: MissionColors
        @Composable
        get() = LocalExtendedColors.current

    val typography: MissionTypography
        @Composable
        get() = LocalExtendedTypography.current

    val shapes: MissionShapes
        @Composable
        get() = LocalExtendedShape.current
}

@Composable
fun MissionTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val typography = Typography
    val shapes = Shapes

    CompositionLocalProvider(
        LocalExtendedColors provides colors,
        LocalExtendedTypography provides typography,
        LocalExtendedShape provides shapes,
    ) {
        MaterialTheme(
            content = content,
        )
    }
}