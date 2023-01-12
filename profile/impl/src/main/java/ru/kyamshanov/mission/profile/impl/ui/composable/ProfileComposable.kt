package ru.kyamshanov.mission.profile.impl.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.profile.api.di.ProfileComponent
import ru.kyamshanov.mission.profile.impl.di.ModuleComponent
import ru.kyamshanov.mission.profile.impl.ui.viewmodel.ProfileViewModel

@Composable
internal fun ProfileComposable(
    component: ModuleComponent = requireNotNull(Di.getInternalComponent<ProfileComponent, ModuleComponent>()),

    viewModel: ProfileViewModel = viewModel { component.profileViewModel }
) {
    val screenState = viewModel.screenState.collectAsState(initial = null)

    screenState.value
        ?.let { ProfileView(screenState = it, viewModel = viewModel) }
        ?: ProfileLoaderView()
}