package ru.kyamshanov.mission.project_view.impl.di

import dagger.Binds
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.project_view.api.navigation.ProjectLauncher
import ru.kyamshanov.mission.project_view.impl.data.api.ProjectApi
import ru.kyamshanov.mission.project_view.impl.data.api.ProjectApiImpl
import ru.kyamshanov.mission.project_view.impl.data.interactor.ProjectInteractorImpl
import ru.kyamshanov.mission.project_view.impl.data.interactor.TaskPointsAnalyticsInteractorImpl
import ru.kyamshanov.mission.project_view.impl.data.usecase.GetProjectUseCaseImpl
import ru.kyamshanov.mission.project_view.impl.domain.interactor.ProjectInteractor
import ru.kyamshanov.mission.project_view.impl.domain.interactor.TaskPointsAnalyticsInteractor
import ru.kyamshanov.mission.project_view.impl.domain.usecase.GetProjectUseCase
import ru.kyamshanov.mission.project_view.impl.domain.usecase.TaskStagePresentUseCase
import ru.kyamshanov.mission.project_view.impl.ui.navigation.ProjectLauncherImpl
import ru.kyamshanov.mission.project_view.impl.ui.usecase.TaskStagePresentUseCaseImpl

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

    @Binds
    @ComponentItem
    fun TaskStagePresentUseCaseImpl.bindTaskStagePresentUseCase(): TaskStagePresentUseCase

    @Binds
    fun ProjectInteractorImpl.bindProjectInteractor(): ProjectInteractor

    @Binds
    fun TaskPointsAnalyticsInteractorImpl.bindTaskPointsAnalyticsInteractor(): TaskPointsAnalyticsInteractor
}