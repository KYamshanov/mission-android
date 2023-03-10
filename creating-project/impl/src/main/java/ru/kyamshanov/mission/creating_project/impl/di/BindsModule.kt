package ru.kyamshanov.mission.creating_project.impl.di

import dagger.Binds
import ru.kyamshanov.mission.creating_project.api.navigation.CreatingProjectLauncher
import ru.kyamshanov.mission.creating_project.impl.data.api.ProjectApi
import ru.kyamshanov.mission.creating_project.impl.data.api.ProjectApiImpl
import ru.kyamshanov.mission.creating_project.impl.data.gateway.ProjectGatewayImpl
import ru.kyamshanov.mission.creating_project.impl.domain.gateway.ProjectGateway
import ru.kyamshanov.mission.creating_project.impl.domain.interactor.ProjectInteractor
import ru.kyamshanov.mission.creating_project.impl.domain.interactor.ProjectInteractorImpl
import ru.kyamshanov.mission.creating_project.impl.domain.usecase.OpenFindingUserScreenUseCase
import ru.kyamshanov.mission.creating_project.impl.domain.usecase.OpenFindingUserScreenUseCaseImpl
import ru.kyamshanov.mission.creating_project.impl.domain.usecase.OpenProjectScreenUseCase
import ru.kyamshanov.mission.creating_project.impl.domain.usecase.OpenProjectScreenUseCaseImpl
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.creating_project.impl.ui.navigation.CreatingProjectLauncherImpl

@dagger.Module
internal interface BindsModule {

    @Binds
    @ComponentItem
    fun bindCreatingProjectLauncher(impl : CreatingProjectLauncherImpl): CreatingProjectLauncher

    @Binds
    @ComponentItem
    fun OpenFindingUserScreenUseCaseImpl.bindOpenFindingUserScreenUseCase(): OpenFindingUserScreenUseCase

    @Binds
    @ComponentItem
    fun ProjectApiImpl.bindProjectApi(): ProjectApi

    @Binds
    @ComponentItem
    fun ProjectGatewayImpl.bindProjectGateway(): ProjectGateway

    @Binds
    @ComponentItem
    fun ProjectInteractorImpl.binProjectInteractor() : ProjectInteractor

    @Binds
    @ComponentItem
    fun OpenProjectScreenUseCaseImpl.binOpenProjectScreenUseCase() : OpenProjectScreenUseCase
}