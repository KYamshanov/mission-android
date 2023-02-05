package ru.kyamshanov.mission.main_screen_feature.impl.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.kyamshanov.mission.main_screen_feature.impl.ui.viewmodel.NavigationBarViewModel
import ru.kyamshanov.mission.ui_core.R.drawable
import ru.kyamshanov.mission.ui_core.R.string
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@Composable
internal fun NavigationBarComposable(
    navigationBarViewModel: NavigationBarViewModel
) {
    Box(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.CenterEnd
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = drawable.ic_profile),
                contentDescription = stringResource(id = string.profile),
                colorFilter = ColorFilter.tint(MissionTheme.colors.backgroundItem),
                modifier = Modifier
                    .size(40.dp)
                    .clickable { navigationBarViewModel.openProfile() }
            )
            Text(text = stringResource(string.profile))
        }
    }
}