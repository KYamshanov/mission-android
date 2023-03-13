package ru.kyamshanov.mission.ui_core.ui.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

@Immutable
data class MissionShapes(
    val small: CornerBasedShape,
    val medium: CornerBasedShape,
    val mediumHuge: CornerBasedShape,
    val large: CornerBasedShape,
)

internal val Shapes = MissionShapes(
    small = RoundedCornerShape(5.dp),
    medium = RoundedCornerShape(10.dp),
    mediumHuge = RoundedCornerShape(15.dp),
    large = RoundedCornerShape(40.dp)
)

internal val LocalExtendedShape = staticCompositionLocalOf {
    MissionShapes(
        mediumHuge = RoundedCornerShape(0),
        small = RoundedCornerShape(0),
        medium = RoundedCornerShape(0),
        large = RoundedCornerShape(0),
    )
}