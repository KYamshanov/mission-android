package ru.kyamshanov.mission.authentication.impl.di

import dagger.Binds
import ru.kyamshanov.mission.authentication.AuthenticationLauncher
import ru.kyamshanov.mission.authentication.impl.ui.navigation.AuthenticationLauncherImpl
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem

@dagger.Module
internal interface BindsModule {

    @Binds
    @ComponentItem
    fun bindAuthenticationLauncher(impl: AuthenticationLauncherImpl): AuthenticationLauncher
}