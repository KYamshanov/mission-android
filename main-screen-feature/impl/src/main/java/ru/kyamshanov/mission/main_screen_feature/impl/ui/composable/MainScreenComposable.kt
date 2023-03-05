package ru.kyamshanov.mission.main_screen_feature.impl.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.main_screen_feature.api.di.MainScreenComponent
import ru.kyamshanov.mission.main_screen_feature.impl.di.ModuleComponent
import ru.kyamshanov.mission.main_screen_feature.impl.ui.viewmodel.NavigationBarViewModel
import ru.kyamshanov.mission.session_front.api.di.SessionFrontComponent
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@Composable
internal fun MainScreenComposable(
    moduleComponent: ModuleComponent = requireNotNull(Di.getInternalComponent<MainScreenComponent, ModuleComponent>()),
    sessionFrontComponent: SessionFrontComponent = requireNotNull(Di.getComponent()),

    navigationBarViewModel: NavigationBarViewModel = viewModel { moduleComponent.navigationBarViewModel }
) {

    SideEffect {
        println("Redraw MainScreen")
    }

    Scaffold(
        backgroundColor = MissionTheme.colors.background,
        bottomBar = {
            NavigationBarComposable(
                navigationBarViewModel = navigationBarViewModel,
                sessionInfo = sessionFrontComponent.sessionInfo
            )
        },
    ) {
        Box(
            Modifier
                .fillMaxSize()
        ) {

        }
    }
}