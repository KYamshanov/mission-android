package ru.kyamshanov.mission.finding_user.impl.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.finding_user.api.di.FindingUserComponent
import ru.kyamshanov.mission.finding_user.impl.di.ModuleComponent
import ru.kyamshanov.mission.finding_user.impl.domain.model.InternalSearchStrategy
import ru.kyamshanov.mission.finding_user.impl.domain.model.UserInfo
import ru.kyamshanov.mission.finding_user.impl.domain.usecase.ObtainUserUseCase
import ru.kyamshanov.mission.finding_user.impl.domain.usecase.ObtainUserUseCase.SearchInfo
import ru.kyamshanov.mission.finding_user.impl.domain.usecase.SelectUserUseCase
import ru.kyamshanov.mission.ui_core.ui.components.Loader
import ru.kyamshanov.mission.ui_core.ui.components.Search
import ru.kyamshanov.mission.ui_core.ui.components.Surface
import ru.kyamshanov.mission.ui_core.ui.components.TopBar
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@Composable
internal fun FindingUserComposable(
    searchStrategy: InternalSearchStrategy,
    moduleComponent: ModuleComponent = requireNotNull(Di.getInternalComponent<FindingUserComponent, ModuleComponent>()),
    obtainUserUseCase: ObtainUserUseCase = moduleComponent.obtainUserUseCaseFactory.create(searchStrategy),
    selectUserUseCase: SelectUserUseCase = moduleComponent.selectUserUseCase,
) {

    val searchStringState = rememberSaveable { mutableStateOf("") }
    val userListState = rememberSaveable { mutableStateOf(emptyList<UserInfo>()) }
    var loadingState by rememberSaveable { mutableStateOf(true) }

    LaunchedEffect(key1 = searchStringState.value) {
        delay(600)
        loadingState = true
        obtainUserUseCase.get(
            searchInfo = SearchInfo(
                name = searchStringState.value,
            )
        ).onSuccess { userListState.value = it }
        loadingState = false
    }

    if (loadingState) Loader { moduleComponent.navigator.exit() }

    Surface(
        verticalScroll = false,
        topContent = {
            TopBar(title = "Поиск", navigationListener = { moduleComponent.navigator.exit() })
        }
    ) {
        Search(value = searchStringState.value, onValueChange = { text -> searchStringState.value = text })

        LazyColumn {
            userListState.value.forEach { userInfo ->
                item {
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = userInfo.name,
                        style = MissionTheme.typography.titleSecondary,
                        modifier = Modifier.clickable { selectUserUseCase.select(userInfo) })
                }
            }
        }
    }
}