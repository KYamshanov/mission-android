package ru.kyamshanov.mission.creating_project.impl.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.kyamshanov.mission.creating_project.api.di.CreatingProjectComponent
import ru.kyamshanov.mission.creating_project.impl.di.ModuleComponent
import ru.kyamshanov.mission.creating_project.impl.domain.models.CreatingProjectInfo
import ru.kyamshanov.mission.creating_project.impl.domain.models.Participant
import ru.kyamshanov.mission.creating_project.impl.domain.usecase.OpenFindingUserScreenUseCase
import ru.kyamshanov.mission.creating_project.impl.ui.viewmodel.CreatingProjectViewModel
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.finding_user.api.model.SelectedUserInfo
import ru.kyamshanov.mission.finding_user.api.navigation.SELECTED_USER_EXTRA_KEY
import ru.kyamshanov.mission.navigation_core.api.ResultProvider
import ru.kyamshanov.mission.navigation_core.api.di.NavigationComponent
import ru.kyamshanov.mission.ui_core.R.drawable
import ru.kyamshanov.mission.ui_core.ui.components.CellInput
import ru.kyamshanov.mission.ui_core.ui.components.MainButton
import ru.kyamshanov.mission.ui_core.ui.components.SecondaryButton
import ru.kyamshanov.mission.ui_core.ui.components.Surface
import ru.kyamshanov.mission.ui_core.ui.components.TopBar
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@Composable
internal fun CreatingProjectComposable(
    moduleComponent: ModuleComponent = requireNotNull(Di.getInternalComponent<CreatingProjectComponent, ModuleComponent>()),

    openFindingUserScreenUseCase: OpenFindingUserScreenUseCase = moduleComponent.openFindingUserScreenUseCase,
    resultProvider: ResultProvider = requireNotNull(Di.getComponent<NavigationComponent>()).resultProvider,
    creatingProjectViewModel: CreatingProjectViewModel = viewModel { moduleComponent.creatingProjectViewModel },
) {
    val nameState = rememberSaveable { mutableStateOf("") }
    val descriptionState = rememberSaveable { mutableStateOf("") }
    val participantsState = rememberSaveable { mutableStateOf(emptyList<SelectedUserInfo>()) }
    val screenState = creatingProjectViewModel.screenState.collectAsState()

    val returnedSelectedUser = resultProvider.get<SelectedUserInfo?>(SELECTED_USER_EXTRA_KEY, null)
    returnedSelectedUser?.let { usersInfo ->
        participantsState.value = participantsState.value.toMutableList()
            .apply { add(usersInfo) }
    }

    if (screenState.value.hasCreatingError) SomethingWentWrongDialog { creatingProjectViewModel.hideCreatingError() }

    Surface(
        topContent = {
            TopBar(title = "Создание проекта", navigationListener = creatingProjectViewModel::clickOnBack)
        },
        bottomContent = {
            MainButton(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                onClick = {
                    val creatingProjectInfo = CreatingProjectInfo(
                        name = nameState.value,
                        description = descriptionState.value,
                        participants = participantsState.value.map { Participant(it.id) }
                    )
                    creatingProjectViewModel.createAndOpenProject(creatingProjectInfo)
                }, label = "Создать проект"
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            CellInput(
                value = nameState.value,
                onValueChange = { text -> nameState.value = text },
                label = "Название проекта"
            )

            Spacer(modifier = Modifier.height(16.dp))

            CellInput(
                value = descriptionState.value,
                onValueChange = { text -> descriptionState.value = text },
                label = "Описание проекта",
                maxLines = Int.MAX_VALUE
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Участники", style = MissionTheme.typography.title)

            Spacer(modifier = Modifier.height(10.dp))

            if (participantsState.value.isEmpty()) {
                Text(text = "Участники не выбраны", style = MissionTheme.typography.titleSecondary)
            } else {
                participantsState.value.forEach {
                    RemovableParticipant(it) {
                        participantsState.value = participantsState.value.toMutableList().apply { remove(it) }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            SecondaryButton(
                onClick = { openFindingUserScreenUseCase.open() },
                label = "Добавить участника"
            )
        }
    }
}

@Composable
internal fun RemovableParticipant(participant: SelectedUserInfo, onRemove: () -> Unit) {
    Row {
        Image(
            modifier = Modifier.clickable { onRemove() },
            painter = painterResource(id = drawable.close_circle),
            contentDescription = "Удалить",
            colorFilter = ColorFilter.tint(MissionTheme.colors.wrong),
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(text = participant.name, style = MissionTheme.typography.titleSecondary)

    }
}

