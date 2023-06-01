package ru.kyamshanov.mission.profile.impl.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.kyamshanov.mission.profile.impl.domain.model.AttachedProjectModel
import ru.kyamshanov.mission.profile.impl.ui.model.ProfileScreenState
import ru.kyamshanov.mission.profile.impl.ui.viewmodel.ProfileViewModel
import ru.kyamshanov.mission.ui_core.ui.components.ComplexCell
import ru.kyamshanov.mission.ui_core.ui.components.MainButton
import ru.kyamshanov.mission.ui_core.ui.components.TextFieldCompose
import ru.kyamshanov.mission.ui_core.ui.components.TopBar
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@Composable
internal fun ProfileView(
    screenState: ProfileScreenState.Info,
    viewModel: ProfileViewModel,
    projects: List<AttachedProjectModel>,
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
            text = screenState.lastname ?: "Не установлено",
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
        Spacer(modifier = Modifier.height(10.dp))

        ComplexCell {
            item {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Активные проекты",
                    style = MissionTheme.typography.title
                )
            }
            item {
                Column {
                    projects.forEach {
                        Text(
                            text = "${it.title} (${it.userRole.toName()})",
                            style = MissionTheme.typography.titleSecondary
                        )
                    }
                }
            }
        }
    }
}

private fun AttachedProjectModel.Role.toName() = when (this) {
    AttachedProjectModel.Role.PARTICIPANT -> "участник"
    AttachedProjectModel.Role.LEADER -> "капитан"
    AttachedProjectModel.Role.MENTOR -> "наставник"
}