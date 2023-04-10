package ru.kyamshanov.mission.project_view.impl.di

import dagger.Provides
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.task.view.api.di.TaskViewComponent
import ru.kyamshanov.mission.task.view.api.navigation.TaskViewLauncher

@dagger.Module
internal class ProvidesModule {

    @Provides
    @ComponentItem
    fun provideTaskViewLauncher(): TaskViewLauncher =
        requireNotNull(Di.getComponent<TaskViewComponent>()).launcher
}