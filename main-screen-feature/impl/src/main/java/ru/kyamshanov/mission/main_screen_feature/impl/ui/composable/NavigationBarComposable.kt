package ru.kyamshanov.mission.main_screen_feature.impl.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.kyamshanov.mission.main_screen_feature.impl.ui.viewmodel.NavigationBarViewModel
import ru.kyamshanov.mission.session_front.api.SessionInfo
import ru.kyamshanov.mission.session_front.api.model.UserRole.MANAGER
import ru.kyamshanov.mission.session_front.api.session.LoggedSession
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@Composable
internal fun NavigationBarComposable(
    modifier: Modifier = Modifier,
    navigationBarViewModel: NavigationBarViewModel,
    sessionInfo: SessionInfo,
) {
    val isManager = (sessionInfo.session as? LoggedSession)?.userInfo?.roles?.contains(MANAGER) ?: false

    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {

        NavigationHorizontalGravityItem(
            modifier = Modifier.clickable { navigationBarViewModel.openProfile() },
            imagePainter = painterResource(id = ru.kyamshanov.mission.ui_core.R.drawable.ic_profile),
            description = stringResource(id = ru.kyamshanov.mission.ui_core.R.string.profile),
            horizontalAlignment = Alignment.CenterEnd,
        )
        if (isManager) {
            NavigationHorizontalGravityItem(
                modifier = Modifier.clickable { navigationBarViewModel.openCreatingProjectScreen() },
                imagePainter = painterResource(id = ru.kyamshanov.mission.ui_core.R.drawable.ic_rounded_plus),
                description = stringResource(id = ru.kyamshanov.mission.ui_core.R.string.create),
                horizontalAlignment = Alignment.Center,
            )
        }
    }
}

@Composable
internal fun NavigationHorizontalGravityItem(
    modifier: Modifier,
    imagePainter: Painter,
    description: String,
    horizontalAlignment: Alignment = Alignment.Center,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = horizontalAlignment,
    ) {
        NavigationItem(
            modifier = modifier,
            imagePainter = imagePainter,
            description = description,
        )
    }
}

@Composable
internal fun NavigationItem(
    modifier: Modifier,
    imagePainter: Painter,
    description: String,
) {
    Column(
        modifier = modifier
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = imagePainter,
            contentDescription = description,
            colorFilter = ColorFilter.tint(MissionTheme.colors.backgroundItem),
            modifier = Modifier.size(40.dp),
        )
        Text(text = description)
    }
}

