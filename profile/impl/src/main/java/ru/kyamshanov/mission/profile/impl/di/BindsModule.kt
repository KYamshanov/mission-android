package ru.kyamshanov.mission.profile.impl.di

import dagger.Binds
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.profile.api.navigation.ProfileLauncher
import ru.kyamshanov.mission.profile.impl.ui.navigation.ProfileLauncherImpl

@dagger.Module
internal interface BindsModule {

    @Binds
    @ComponentItem
    fun bindProfileLauncher(impl: ProfileLauncherImpl): ProfileLauncher
}