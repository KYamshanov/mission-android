package ru.kyamshanov.mission.ui_core.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.kyamshanov.mission.ui_core.R
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@Composable
fun Icon(
    modifier: Modifier = Modifier,
    @DrawableRes iconResource: Int,
    contentDescription: String,
) = Icon(
    modifier = modifier,
    painter = painterResource(id = iconResource),
    contentDescription = contentDescription,
    onClick = null
)

@Composable
fun Icon(
    modifier: Modifier = Modifier,
    @DrawableRes iconResource: Int,
    contentDescription: String,
    onClick: () -> Unit,
) = Icon(
    modifier = modifier,
    painter = painterResource(id = iconResource),
    contentDescription = contentDescription,
    onClick = onClick
)

@Composable
fun Icon(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String,
    colorFilter: ColorFilter = ColorFilter.tint(MissionTheme.colors.darkSecondary),
    onClick: (() -> Unit)?,
) = Image(
    modifier = modifier
        .run { onClick?.let { listener -> clip(CircleShape).clickable(onClick = listener) } ?: this }
        .padding(5.dp),
    painter = painter,
    contentDescription = contentDescription,
    colorFilter = colorFilter
)

@Preview
@Composable
fun IconPreview() {
    MissionTheme {
        Box(modifier = Modifier.padding(15.dp)) {
            Icon(
                iconResource = R.drawable.ic_search,
                contentDescription = "Поиск",
                onClick = { }
            )
        }
    }
}