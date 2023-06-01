package ru.kyamshanov.mission.profile.impl.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.profile.api.di.ProfileComponent
import ru.kyamshanov.mission.profile.impl.di.ModuleComponent
import ru.kyamshanov.mission.profile.impl.ui.viewmodel.ProfileViewModel
import ru.kyamshanov.mission.ui_core.ui.components.SomethingWentWrongDialog

@Composable
internal fun ProfileComposable(
    component: ModuleComponent = requireNotNull(Di.getInternalComponent<ProfileComponent, ModuleComponent>()),

    viewModel: ProfileViewModel = viewModel { component.profileViewModel },
) {

    val screenState by viewModel.screenState.collectAsState()

    SideEffect {
        println("TEST ${screenState.projects.size}")
    }

    screenState.info
        ?.let { ProfileView(screenState = it, viewModel = viewModel, projects = screenState.projects) }
        ?: ProfileLoaderView()

    if (screenState.somethingWentWrong) {
        SomethingWentWrongDialog(
            onDismissRequest = viewModel::closeSomethingWentWrong,
            onConfirm = viewModel::closeSomethingWentWrong
        )
    }
}