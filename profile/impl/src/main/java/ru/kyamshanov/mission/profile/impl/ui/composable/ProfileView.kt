package ru.kyamshanov.mission.profile.impl.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.kyamshanov.mission.profile.impl.ui.model.ProfileScreenState
import ru.kyamshanov.mission.profile.impl.ui.viewmodel.ProfileViewModel
import ru.kyamshanov.mission.ui_core.ui.components.MainButton
import ru.kyamshanov.mission.ui_core.ui.components.TextFieldCompose
import ru.kyamshanov.mission.ui_core.ui.components.TopBar

@Composable
internal fun ProfileView(
    screenState: ProfileScreenState.Info,
    viewModel: ProfileViewModel,
) = ru.kyamshanov.mission.ui_core.ui.components.Surface(
    modifier = Modifier.padding(16.dp),
    topContent = {
        TopBar(title = "Профиль", navigationListener = viewModel::clickOnBack)
    },
    bottomContent = {
        MainButton(label = "Выйти", onClick = viewModel::exit)
    }
) {
    Column {

        TextFieldCompose(label = "Логин", text = screenState.login, editable = true, onValueChange = {})
        Spacer(modifier = Modifier.height(10.dp))
        TextFieldCompose(
            label = "Имя",
            text = screenState.firstname ?: "Не установлено",
            editable = false,
            onValueChange = {})
        Spacer(modifier = Modifier.height(10.dp))
        TextFieldCompose(
            label = "Фамилия",
            text = screenState.firstname ?: "Не установлено",
            editable = false,
            onValueChange = {})
        if (screenState.patronymic != null) {
            Spacer(modifier = Modifier.height(10.dp))
            TextFieldCompose(
                label = "Отчество",
                text = screenState.patronymic,
                editable = false,
                onValueChange = {})
        }
    }
}