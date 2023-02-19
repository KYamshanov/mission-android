package ru.kyamshanov.mission.creating_project.impl.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import ru.kyamshanov.mission.creating_project.api.di.CreatingProjectComponent
import ru.kyamshanov.mission.creating_project.impl.di.ModuleComponent
import ru.kyamshanov.mission.creating_project.impl.domain.OpenFindingUserScreenUseCase
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.finding_user.api.model.SelectedUserInfo
import ru.kyamshanov.mission.finding_user.api.navigation.SELECTED_USER_EXTRA_KEY
import ru.kyamshanov.mission.navigation_core.api.ResultProvider
import ru.kyamshanov.mission.navigation_core.api.di.NavigationComponent

@Composable
internal fun CreatingProjectComposable(
    moduleComponent: ModuleComponent = requireNotNull(Di.getInternalComponent<CreatingProjectComponent, ModuleComponent>()),

    openFindingUserScreenUseCase: OpenFindingUserScreenUseCase = moduleComponent.openFindingUserScreenUseCase,
    resultProvider: ResultProvider = requireNotNull(Di.getComponent<NavigationComponent>()).resultProvider
) {

    val returnedUser = resultProvider.get<SelectedUserInfo?>(SELECTED_USER_EXTRA_KEY, null)

    val usersState = rememberSaveable { mutableStateOf(emptyList<SelectedUserInfo>()) }

    returnedUser?.let { usersInfo ->
        usersState.value = usersState.value.toMutableList()
            .apply { add(usersInfo) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        val nameState = rememberSaveable { mutableStateOf("") }
        val descriptionState = rememberSaveable { mutableStateOf("") }

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
            usersState.value.forEach {
                item { Text(text = "${it.firstName} ${it.lastName} ${it.patronymic}") }
            }
        }

    }
}