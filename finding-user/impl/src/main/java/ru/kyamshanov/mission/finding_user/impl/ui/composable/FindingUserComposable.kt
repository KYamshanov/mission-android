package ru.kyamshanov.mission.finding_user.impl.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import kotlinx.coroutines.delay
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.finding_user.api.di.FindingUserComponent
import ru.kyamshanov.mission.finding_user.impl.di.ModuleComponent
import ru.kyamshanov.mission.finding_user.impl.domain.model.UserInfo
import ru.kyamshanov.mission.finding_user.impl.domain.usecase.ObtainUserUseCase
import ru.kyamshanov.mission.finding_user.impl.domain.usecase.ObtainUserUseCase.SearchInfo
import ru.kyamshanov.mission.finding_user.impl.domain.usecase.SelectUserUseCase

@Composable
internal fun FindingUserComposable(
    moduleComponent: ModuleComponent = requireNotNull(Di.getInternalComponent<FindingUserComponent, ModuleComponent>()),
    obtainUserUseCase: ObtainUserUseCase = moduleComponent.obtainUserUseCase,
    selectUserUseCase: SelectUserUseCase = moduleComponent.selectUserUseCase,
) {

    val firstNameState = rememberSaveable { mutableStateOf("") }
    val secondNameState = rememberSaveable { mutableStateOf("") }

    val userListState = rememberSaveable { mutableStateOf(emptyList<UserInfo>()) }

    LaunchedEffect(key1 = firstNameState.value, key2 = secondNameState.value) {
        delay(1000)
        userListState.value = obtainUserUseCase.get(
            searchInfo = SearchInfo(
                name = firstNameState.value,
            )
        )
    }

    Column {
        TextField(
            value = firstNameState.value,
            onValueChange = { text -> firstNameState.value = text },
            label = { Text(text = "Имя") }
        )
        TextField(
            value = secondNameState.value,
            onValueChange = { text -> secondNameState.value = text },
            label = { Text(text = "Фамилия") }
        )
        LazyColumn {
            userListState.value.forEach { userInfo ->
                item {
                    Button(onClick = { selectUserUseCase.select(userInfo) }) {
                        Text(text = "${userInfo.name} ${userInfo.age} ${userInfo.id}")
                    }
                }
            }
        }
    }
}