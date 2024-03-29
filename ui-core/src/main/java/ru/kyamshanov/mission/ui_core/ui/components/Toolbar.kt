package ru.kyamshanov.mission.ui_core.ui.components

import android.content.res.Configuration.ORIENTATION_PORTRAIT
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import ru.kyamshanov.mission.ui_core.R
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String,
    navigationListener: () -> Unit,
) = TopBar(
    modifier = modifier,
    navigationListener = navigationListener,
    title = {
        Text(
            modifier = modifier.align(Alignment.Center),
            text = title,
            style = MissionTheme.typography.topBarTitle
        )
    }
)

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    navigationListener: () -> Unit,
) = TopBar(
    modifier = modifier,
    navigationListener = navigationListener,
    title = {
        Column(modifier = Modifier.align(Alignment.Center).fillMaxWidth()) {
            Text(
                modifier = modifier.align(Alignment.CenterHorizontally),
                text = title,
                style = MissionTheme.typography.topBarSecondaryTitle
            )
            Text(
                modifier = modifier.align(Alignment.CenterHorizontally),
                text = subtitle,
                style = MissionTheme.typography.topBarSubtitle
            )
        }
    }
)

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: @Composable BoxScope.() -> Unit,
    navigationListener: () -> Unit,
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = MissionTheme.colors.secondary,
        contentColor = MissionTheme.colors.primary,
        contentPadding = when (LocalConfiguration.current.orientation) {
            ORIENTATION_PORTRAIT -> WindowInsets.statusBars.asPaddingValues()
            else -> WindowInsets.systemBars.asPaddingValues()
        }
    ) {
        val localDensity = LocalDensity.current
        val iconSizeState = remember { mutableStateOf(IntSize.Zero) }

        Image(
            modifier = Modifier.run {
                padding(10.dp)
                    .clip(CircleShape)
                    .clickable(onClick = navigationListener)
                    .onSizeChanged { iconSizeState.value = it }
            },
            painter = painterResource(id = R.drawable.arrow_back),
            contentDescription = "come back",
            colorFilter = ColorFilter.tint(MissionTheme.colors.primary)
        )

        Box(modifier = Modifier.fillMaxSize().padding(end = with(localDensity) { iconSizeState.value.width.toDp() })) {
            title.invoke(this)
        }
    }
}