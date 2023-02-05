package ru.kyamshanov.mission.authentication.impl.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.kyamshanov.mission.authentication.di.AuthenticationComponent
import ru.kyamshanov.mission.authentication.impl.di.ModuleComponent
import ru.kyamshanov.mission.authentication.impl.ui.model.AuthenticationState.LOADER
import ru.kyamshanov.mission.authentication.impl.ui.model.AuthenticationState.LOGIN
import ru.kyamshanov.mission.authentication.impl.ui.viewmodel.AuthenticationViewModel
import ru.kyamshanov.mission.authentication.impl.ui.viewmodel.LoginViewModel
import ru.kyamshanov.mission.di_dagger.impl.Di

@Composable
internal fun AuthenticationComposable(
    moduleComponent: ModuleComponent = requireNotNull(Di.getInternalComponent<AuthenticationComponent, ModuleComponent>()),

    authViewModel: AuthenticationViewModel = viewModel { moduleComponent.authenticationViewModel },
    loginViewModel: LoginViewModel = viewModel { moduleComponent.loginViewModel }
) {
    val screenState = authViewModel.screenState.collectAsState(initial = LOADER)

    when (screenState.value) {
        LOADER -> LoaderComposable()
        LOGIN -> LoginComposable(loginViewModel)
    }
}