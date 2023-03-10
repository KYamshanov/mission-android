package ru.kyamshanov.mission.ui_core.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class MissionColors(
    val background: Color,
    val mainButton: Color,
    val baseButtonText: Color,
    val input: Color,
    val baseText: Color,
    val secondText: Color,
    val secondButton: Color,
    val secondButtonText: Color,
    val success: Color,
    val wrong: Color,
    val focusedBorder: Color,
    val border: Color,
)

internal val White = Color(0xFFFFFFFF)
internal val Black = Color(0xFF000000)
internal val Purple200 = Color(0xFFBB86FC)
internal val Purple500 = Color(0xFF6200EE)
internal val Purple700 = Color(0xFF3700B3)
internal val Teal200 = Color(0xFF03DAC5)
internal val RedPleasant = Color(0xFFFD3223)
internal val WhitePleasant = Color(0xFFF2F4FB)
internal val BluePleasant = Color(0xFF164ACF)
internal val BlackPleasant = Color(0xFF3C3C3C)
internal val GrayPleasant = Color(0xFFD9D9D9)
internal val GreenPleasant = Color(0xFF17830E)

internal val DarkColorPalette = MissionColors(
    background = WhitePleasant,
    mainButton = Color.Blue,
    baseButtonText = White,
    input = White,
    baseText = Black,
    secondText = BlackPleasant,
    secondButton = GrayPleasant,
    secondButtonText = Black,
    success = GreenPleasant,
    wrong = RedPleasant,
    focusedBorder = Black,
    border = BlackPleasant,
)

internal val LightColorPalette = MissionColors(
    background = WhitePleasant,
    mainButton = Color.Blue,
    baseButtonText = White,
    input = White,
    baseText = Black,
    secondText = BlackPleasant,
    secondButton = GrayPleasant,
    secondButtonText = Black,
    success = GreenPleasant,
    wrong = RedPleasant,
    focusedBorder = Black,
    border = BlackPleasant,
)

