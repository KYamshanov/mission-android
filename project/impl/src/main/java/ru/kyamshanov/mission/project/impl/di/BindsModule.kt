package ru.kyamshanov.mission.project.impl.di

import dagger.Binds
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.project.api.navigation.ProjectLauncher
import ru.kyamshanov.mission.project.impl.ui.navigation.ProjectLauncherImpl

@dagger.Module
internal interface BindsModule {

    @Binds
    @ComponentItem
    fun ProjectLauncherImpl.bindProjectLauncher(): ProjectLauncher
}