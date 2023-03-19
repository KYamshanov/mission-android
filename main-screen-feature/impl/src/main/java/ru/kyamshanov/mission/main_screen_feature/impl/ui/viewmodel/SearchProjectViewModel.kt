package ru.kyamshanov.mission.main_screen_feature.impl.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.project.api.di.ProjectComponent
import ru.kyamshanov.mission.project.api.navigation.ProjectLauncher
import ru.kyamshanov.mission.search_project.api.domain.SearchProjectUseCase
import ru.kyamshanov.mission.search_project.api.domain.model.PageIndex
import ru.kyamshanov.mission.search_project.api.domain.model.ProjectInfo

internal class SearchProjectViewModel @Inject constructor(
    private val searchProjectUseCase: SearchProjectUseCase,
    private val projectLauncher: ProjectLauncher,
) : ViewModel() {

    private val _projectsState = MutableStateFlow(emptyList<ProjectInfo>())
    val projectsState = _projectsState.asStateFlow()

    init {
        viewModelScope.launch {
            searchProjectUseCase.findAll(PageIndex(0, PAGE_SIZE))
                .onSuccess {
                    _projectsState.value = it
                }
        }
    }

    private var searchJob: Job? = null

    fun searchByName(projectName: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(1000L)
            searchProjectUseCase.searchByName(projectName, PageIndex(0, PAGE_SIZE))
                .onSuccess {
                    _projectsState.value = it
                }
        }
    }

    fun openProject(projectId: String) {
        projectLauncher.launch(projectId = projectId)
    }

    companion object {

        private const val PAGE_SIZE = 50
    }
}