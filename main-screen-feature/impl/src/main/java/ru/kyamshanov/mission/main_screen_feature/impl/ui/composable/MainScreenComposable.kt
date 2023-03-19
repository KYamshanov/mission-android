package ru.kyamshanov.mission.main_screen_feature.impl.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.main_screen_feature.api.di.MainScreenComponent
import ru.kyamshanov.mission.main_screen_feature.impl.di.ModuleComponent
import ru.kyamshanov.mission.main_screen_feature.impl.ui.viewmodel.NavigationBarViewModel
import ru.kyamshanov.mission.main_screen_feature.impl.ui.viewmodel.SearchProjectViewModel
import ru.kyamshanov.mission.session_front.api.di.SessionFrontComponent
import ru.kyamshanov.mission.ui_core.ui.components.Search
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@Composable
internal fun MainScreenComposable(
    moduleComponent: ModuleComponent = requireNotNull(Di.getInternalComponent<MainScreenComponent, ModuleComponent>()),
    sessionFrontComponent: SessionFrontComponent = requireNotNull(Di.getComponent()),

    navigationBarViewModel: NavigationBarViewModel = viewModel { moduleComponent.navigationBarViewModel },
    searchProjectViewModel: SearchProjectViewModel = viewModel { moduleComponent.searchProjectViewModel },
) {

    SideEffect {
        println("Redraw MainScreen")
    }

    Scaffold(
        backgroundColor = MissionTheme.colors.background,
        bottomBar = {
            NavigationBarComposable(
                navigationBarViewModel = navigationBarViewModel,
                sessionInfo = sessionFrontComponent.sessionInfo
            )
        },
    ) {

        val searchPhraseState = rememberSaveable { mutableStateOf("") }
        val projects by searchProjectViewModel.projectsState.collectAsState()

        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Search(
                value = searchPhraseState.value, onValueChange = {
                    searchPhraseState.value = it
                    searchProjectViewModel.searchByName(it)
                })

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn(modifier = Modifier.padding(5.dp)) {
                projects.forEach { project ->
                    item {
                        Text(
                            modifier = Modifier.clickable { searchProjectViewModel.openProject(project.id) },
                            text = project.title,
                            style = MissionTheme.typography.field
                        )
                    }
                }
            }
        }
    }
}