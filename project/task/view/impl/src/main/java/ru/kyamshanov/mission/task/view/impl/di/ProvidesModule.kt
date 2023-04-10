package ru.kyamshanov.mission.task.view.impl.di

import dagger.Provides
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.task.set_points.api.di.SetPointsComponent
import ru.kyamshanov.mission.task.set_points.api.navigation.SetPointsLauncher

@dagger.Module
internal class ProvidesModule {

    @Provides
    @ComponentItem
    fun provideSetPointLauncher(): SetPointsLauncher = requireNotNull(Di.getComponent<SetPointsComponent>()).launcher
}