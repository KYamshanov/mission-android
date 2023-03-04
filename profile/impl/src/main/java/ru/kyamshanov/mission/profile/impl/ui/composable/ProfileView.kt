package ru.kyamshanov.mission.profile.impl.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.kyamshanov.mission.profile.impl.ui.model.ProfileScreenState
import ru.kyamshanov.mission.profile.impl.ui.viewmodel.ProfileViewModel

@Composable
internal fun ProfileView(
    screenState: ProfileScreenState,
    viewModel: ProfileViewModel,
) {

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            for (role in screenState.roles) {
                item { Text(text = role.name) }
            }
            item { Text(text = screenState.age) }
            item { Text(text = screenState.name) }
        }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            Button(onClick = {
                viewModel.exit()
            }) { Text(text = "Выйти") }
        }

    }
}