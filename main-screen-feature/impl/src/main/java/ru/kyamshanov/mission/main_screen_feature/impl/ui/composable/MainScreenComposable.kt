package ru.kyamshanov.mission.main_screen_feature.impl.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.main_screen_feature.api.di.MainScreenComponent
import ru.kyamshanov.mission.main_screen_feature.impl.di.ModuleComponent
import ru.kyamshanov.mission.main_screen_feature.impl.ui.viewmodel.NavigationBarViewModel
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@Composable
internal fun MainScreenComposable(
    moduleComponent: ModuleComponent = requireNotNull(Di.getInternalComponent<MainScreenComponent, ModuleComponent>()),

    navigationBarViewModel: NavigationBarViewModel = viewModel { moduleComponent.navigationBarViewModel }
) {
    Scaffold(
        backgroundColor = MissionTheme.colors.background,
        bottomBar = { NavigationBarComposable(navigationBarViewModel = navigationBarViewModel) },
        ) {
        Box(
            Modifier
                .fillMaxSize()
        ) {

        }
    }
}