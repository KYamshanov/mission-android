package ru.kyamshanov.mission.profile_facade.impl.di

import dagger.Binds
import dagger.Provides
import ru.kyamshanov.mission.background_registration.api.di.BackgroundRegistrationComponent
import ru.kyamshanov.mission.background_registration.api.ui.navigation.BackgroundRegistrationLauncher
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.di_dagger.impl.Di

@dagger.Module
internal class ProvidesModule {

    @Provides
    @ComponentItem
    fun provideBackgroundRegistrationLauncher(): BackgroundRegistrationLauncher =
        requireNotNull(Di.getComponent<BackgroundRegistrationComponent>()).launcher
}