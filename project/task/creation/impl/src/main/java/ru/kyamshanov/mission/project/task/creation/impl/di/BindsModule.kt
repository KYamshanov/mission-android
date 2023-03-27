package ru.kyamshanov.mission.project.task.creation.impl.di

import dagger.Binds
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.project.task.creation.api.navigation.ProjectTaskCreationLauncher
import ru.kyamshanov.mission.project.task.creation.impl.data.api.ProjectApi
import ru.kyamshanov.mission.project.task.creation.impl.data.api.ProjectApiImpl
import ru.kyamshanov.mission.project.task.creation.impl.data.interactor.CreationTaskInfoInteractorImpl
import ru.kyamshanov.mission.project.task.creation.impl.domain.CreationTaskInfoInteractor
import ru.kyamshanov.mission.project.task.creation.impl.ui.navigation.ProjectTaskCreationLauncherImpl

@dagger.Module
internal interface BindsModule {

    @Binds
    @ComponentItem
    fun bindProfileLauncher(impl: ProjectTaskCreationLauncherImpl): ProjectTaskCreationLauncher

    @Binds
    @ComponentItem
    fun CreationTaskInfoInteractorImpl.bindCreationTaskInfoInteractor(): CreationTaskInfoInteractor

    @Binds
    @ComponentItem
    fun ProjectApiImpl.bindProjectApi() : ProjectApi
}