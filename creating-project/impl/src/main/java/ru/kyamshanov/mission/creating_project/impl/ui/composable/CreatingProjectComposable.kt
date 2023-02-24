package ru.kyamshanov.mission.creating_project.impl.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.kyamshanov.mission.creating_project.api.di.CreatingProjectComponent
import ru.kyamshanov.mission.creating_project.impl.di.ModuleComponent
import ru.kyamshanov.mission.creating_project.impl.domain.models.CreatingProjectInfo
import ru.kyamshanov.mission.creating_project.impl.domain.usecase.OpenFindingUserScreenUseCase
import ru.kyamshanov.mission.creating_project.impl.ui.models.ScreenState
import ru.kyamshanov.mission.creating_project.impl.ui.viewmodel.CreatingProjectViewModel
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.finding_user.api.model.SelectedUserInfo
import ru.kyamshanov.mission.finding_user.api.navigation.SELECTED_USER_EXTRA_KEY
import ru.kyamshanov.mission.navigation_core.api.ResultProvider
import ru.kyamshanov.mission.navigation_core.api.di.NavigationComponent
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
    val selectedProjectUsersState = rememberSaveable { mutableStateOf(emptyList<SelectedUserInfo>()) }
    val screenState = creatingProjectViewModel.screenState.collectAsState()

    val returnedSelectedUser = resultProvider.get<SelectedUserInfo?>(SELECTED_USER_EXTRA_KEY, null)
    returnedSelectedUser?.let { usersInfo ->
        selectedProjectUsersState.value = selectedProjectUsersState.value.toMutableList()
            .apply { add(usersInfo) }
    }

    if (screenState.value.hasCreatingError) SomethingWentWrongDialog { creatingProjectViewModel.hideCreatingError() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MissionTheme.colors.background),
    ) {

        TextField(
            value = nameState.value,
            onValueChange = { text -> nameState.value = text },
            label = { Text(text = "Название") }
        )

        TextField(
            value = descriptionState.value,
            onValueChange = { text -> descriptionState.value = text },
            label = { Text(text = "Описание") }
        )
        Text(text = "Состав команды")
        Button(onClick = { openFindingUserScreenUseCase.open() }) {
            Text(text = "Добавить участника")
        }

        LazyColumn {
            selectedProjectUsersState.value.forEach {
                item { Text(text = "${it.firstName} ${it.lastName} ${it.patronymic}") }
            }
        }

        Button(onClick = {
            val creatingProjectInfo = CreatingProjectInfo(
                name = nameState.value,
                description = descriptionState.value
            )
            creatingProjectViewModel.createAndOpenProject(creatingProjectInfo)
        }) {
            Text(text = "Создать команду")
        }

    }
}

