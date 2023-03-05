package ru.kyamshanov.mission.background_registration.impl.di

import dagger.Binds
import ru.kyamshanov.mission.background_registration.api.ui.navigation.BackgroundRegistrationLauncher
import ru.kyamshanov.mission.background_registration.impl.ui.models.ComposableFieldFactory
import ru.kyamshanov.mission.background_registration.impl.ui.models.ComposableFieldFactoryImpl
import ru.kyamshanov.mission.background_registration.impl.ui.navigation.BackgroundRegistrationLauncherImpl
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem

@dagger.Module
internal interface BindsModule {

    @Binds
    @ComponentItem
    fun bindProfileLauncher(impl: BackgroundRegistrationLauncherImpl): BackgroundRegistrationLauncher

    @Binds
    @ComponentItem
    fun ComposableFieldFactoryImpl.bindComposableFieldFactory(): ComposableFieldFactory
}