package ru.kyamshanov.mission.profile.impl.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ) { Text(text = "Профиль ") }

        Button(onClick = {

        }) { Text(text = "Проверка") }

        Button(onClick = {

        }) { Text(text = "Выйти") }

        Button(onClick = {

        }) { Text(text = "Рефреш") }
    }
}