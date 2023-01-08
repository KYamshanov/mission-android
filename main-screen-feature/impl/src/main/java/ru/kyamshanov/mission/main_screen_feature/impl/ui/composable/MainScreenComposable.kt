package ru.kyamshanov.mission.main_screen_feature.impl.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.profile.api.di.ProfileComponent

@Composable
internal fun MainScreenComposable(
    profileComponent: ProfileComponent = requireNotNull(Di.getComponent())
) {

    Box(
        modifier = Modifier
            .fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Text(text = "Главный экран ",
            modifier = Modifier
                .clickable {
                    profileComponent.launcher.launch()
                }
        )
    }
}