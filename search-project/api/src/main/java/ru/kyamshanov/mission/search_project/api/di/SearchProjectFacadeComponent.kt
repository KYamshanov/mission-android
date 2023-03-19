package ru.kyamshanov.mission.search_project.api.di

import ru.kyamshanov.mission.search_project.api.domain.SearchProjectUseCase

interface SearchProjectFacadeComponent {

    val searchUseCase: SearchProjectUseCase
}