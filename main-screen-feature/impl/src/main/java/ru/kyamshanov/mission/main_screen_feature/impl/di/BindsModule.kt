package ru.kyamshanov.mission.main_screen_feature.impl.di

import dagger.Binds
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.main_screen_feature.api.navigation.MainScreenLauncher
import ru.kyamshanov.mission.main_screen_feature.impl.ui.navigation.MainScreenLauncherImpl

@dagger.Module
internal interface BindsModule {

    @Binds
    @ComponentItem
    fun bindMainScreenLauncher(impl: MainScreenLauncherImpl): MainScreenLauncher
}