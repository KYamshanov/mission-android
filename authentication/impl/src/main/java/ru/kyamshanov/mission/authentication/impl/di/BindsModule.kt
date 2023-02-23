package ru.kyamshanov.mission.authentication.impl.di

import dagger.Binds
import ru.kyamshanov.mission.authentication.domain.AuthenticationLauncher
import ru.kyamshanov.mission.authentication.impl.domain.AuthenticationUseCase
import ru.kyamshanov.mission.authentication.impl.domain.AuthenticationUseCaseImpl
import ru.kyamshanov.mission.authentication.impl.domain.LoginUseCase
import ru.kyamshanov.mission.authentication.impl.domain.LoginUseCaseImpl
import ru.kyamshanov.mission.authentication.impl.ui.navigation.AuthenticationLauncherImpl
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem

@dagger.Module
internal interface BindsModule {

    @Binds
    @ComponentItem
    fun bindAuthenticationLauncher(impl: AuthenticationLauncherImpl): AuthenticationLauncher

    @Binds
    @ComponentItem
    fun bindLoginUseCase(impl: LoginUseCaseImpl): LoginUseCase

    @Binds
    @ComponentItem
    fun bindAuthenticationUseCase(impl: AuthenticationUseCaseImpl): AuthenticationUseCase
}