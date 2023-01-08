package ru.kyamshanov.mission.authentication.impl.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import ru.kyamshanov.mission.authentication.impl.ui.model.AuthenticationState.LOADER
import ru.kyamshanov.mission.authentication.impl.ui.model.AuthenticationState.LOGIN
import ru.kyamshanov.mission.authentication.impl.ui.viewmodel.AuthenticationViewModel
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.main_screen_feature.api.di.MainScreenComponent
import ru.kyamshanov.mission.session_front.api.di.SessionFrontComponent

@Composable
internal fun AuthenticationComposable(
    sessionComponent: SessionFrontComponent = requireNotNull(Di.getComponent()),
    mainScreenLauncher: MainScreenComponent = requireNotNull(Di.getComponent()),

    viewModel: AuthenticationViewModel = androidx.lifecycle.viewmodel.compose.viewModel {
        AuthenticationViewModel(
            sessionComponent = sessionComponent,
            mainScreenLauncher = mainScreenLauncher.launcher
        )
    }
) {
    val screenState = viewModel.screenState.collectAsState(initial = LOADER)

    when (screenState.value) {
        LOADER -> LoaderComposable()
        LOGIN -> LoginComposable()
    }
}