package ru.kyamshanov.mission.project_view.impl.di

import dagger.Binds
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.project_view.api.navigation.ProjectLauncher
import ru.kyamshanov.mission.project_view.impl.data.api.ProjectApi
import ru.kyamshanov.mission.project_view.impl.data.api.ProjectApiImpl
import ru.kyamshanov.mission.project_view.impl.data.usecase.GetProjectUseCaseImpl
import ru.kyamshanov.mission.project_view.impl.domain.usecase.GetProjectUseCase
import ru.kyamshanov.mission.project_view.impl.ui.navigation.ProjectLauncherImpl

@dagger.Module
internal interface BindsModule {

    @Binds
    @ComponentItem
    fun ProjectLauncherImpl.bindProjectLauncher(): ProjectLauncher

    @Binds
    @ComponentItem
    fun ProjectApiImpl.bindProjectApi(): ProjectApi

    @Binds
    @ComponentItem
    fun GetProjectUseCaseImpl.bindGetProjectUseCase(): GetProjectUseCase
}