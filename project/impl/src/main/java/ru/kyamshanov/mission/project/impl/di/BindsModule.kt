package ru.kyamshanov.mission.project.impl.di

import dagger.Binds
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.project.api.navigation.ProjectLauncher
import ru.kyamshanov.mission.project.impl.data.api.ProfileApi
import ru.kyamshanov.mission.project.impl.data.api.ProfileApiImpl
import ru.kyamshanov.mission.project.impl.data.api.ProjectApi
import ru.kyamshanov.mission.project.impl.data.api.ProjectApiImpl
import ru.kyamshanov.mission.project.impl.data.usecase.GetProjectUseCaseImpl
import ru.kyamshanov.mission.project.impl.domain.usecase.GetProjectUseCase
import ru.kyamshanov.mission.project.impl.ui.navigation.ProjectLauncherImpl

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
    fun ProfileApiImpl.bindProfileApi(): ProfileApi

    @Binds
    @ComponentItem
    fun GetProjectUseCaseImpl.bindGetProjectUseCase(): GetProjectUseCase
}