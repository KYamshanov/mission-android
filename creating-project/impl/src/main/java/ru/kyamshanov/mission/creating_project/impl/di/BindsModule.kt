package ru.kyamshanov.mission.creating_project.impl.di

import dagger.Binds
import ru.kyamshanov.mission.creating_project.api.navigation.CreatingProjectLauncher
import ru.kyamshanov.mission.creating_project.impl.domain.OpenFindingUserScreenUseCase
import ru.kyamshanov.mission.creating_project.impl.domain.OpenFindingUserScreenUseCaseImpl
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.creating_project.impl.ui.navigation.CreatingProjectLauncherImpl

@dagger.Module
internal interface BindsModule {

    @Binds
    @ComponentItem
    fun CreatingProjectLauncherImpl.bindCreatingProjectLauncher(): CreatingProjectLauncher

    @Binds
    @ComponentItem
    fun OpenFindingUserScreenUseCaseImpl.bindOpenFindingUserScreenUseCase(): OpenFindingUserScreenUseCase
}