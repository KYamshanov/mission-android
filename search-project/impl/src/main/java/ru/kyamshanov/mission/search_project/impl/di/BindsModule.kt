package ru.kyamshanov.mission.search_project.impl.di

import dagger.Binds
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.search_project.api.domain.SearchProjectUseCase
import ru.kyamshanov.mission.search_project.impl.data.api.ProjectApi
import ru.kyamshanov.mission.search_project.impl.data.api.ProjectApiImpl
import ru.kyamshanov.mission.search_project.impl.data.usecase.SearchProjectUseCaseImpl

@dagger.Module
internal interface BindsModule {

    @Binds
    @ComponentItem
    fun ProjectApiImpl.binProjectApi(): ProjectApi

    @Binds
    @ComponentItem
    fun SearchProjectUseCaseImpl.bindSearchProjectUseCase(): SearchProjectUseCase
}