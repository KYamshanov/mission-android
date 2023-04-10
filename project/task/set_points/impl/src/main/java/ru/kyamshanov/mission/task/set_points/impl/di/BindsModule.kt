package ru.kyamshanov.mission.task.set_points.impl.di

import dagger.Binds
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.task.set_points.api.navigation.SetPointsLauncher
import ru.kyamshanov.mission.task.set_points.impl.data.api.ProjectApi
import ru.kyamshanov.mission.task.set_points.impl.data.api.ProjectApiImpl
import ru.kyamshanov.mission.task.set_points.impl.data.usecase.SetPointsUseCaseImpl
import ru.kyamshanov.mission.task.set_points.impl.domain.SetPointsUseCase
import ru.kyamshanov.mission.task.set_points.impl.ui.navigation.SetPointsLauncherImpl

@dagger.Module
internal interface BindsModule {

    @Binds
    @ComponentItem
    fun ProjectApiImpl.bindProjectApi(): ProjectApi

    @Binds
    @ComponentItem
    fun SetPointsLauncherImpl.bindSetPointsLauncher(): SetPointsLauncher

    @Binds
    @ComponentItem
    fun SetPointsUseCaseImpl.bindSetPointsUseCase(): SetPointsUseCase
}