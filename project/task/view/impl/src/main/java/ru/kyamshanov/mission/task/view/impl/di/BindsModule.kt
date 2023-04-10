package ru.kyamshanov.mission.task.view.impl.di

import dagger.Binds
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.task.view.api.navigation.TaskViewLauncher
import ru.kyamshanov.mission.task.view.impl.data.api.ProjectApi
import ru.kyamshanov.mission.task.view.impl.data.api.ProjectApiImpl
import ru.kyamshanov.mission.task.view.impl.data.usecase.FetchTaskUseCaseImpl
import ru.kyamshanov.mission.task.view.impl.domain.usecase.FetchTaskUseCase
import ru.kyamshanov.mission.task.view.impl.ui.navigation.TaskViewLauncherImpl

@dagger.Module
internal interface BindsModule {

    @Binds
    @ComponentItem
    fun TaskViewLauncherImpl.bindTaskViewLauncher(): TaskViewLauncher

    @Binds
    @ComponentItem
    fun ProjectApiImpl.bindProjectApi() : ProjectApi

    @Binds
    @ComponentItem
    fun FetchTaskUseCaseImpl.bindFetchTaskUseCase() : FetchTaskUseCase
}