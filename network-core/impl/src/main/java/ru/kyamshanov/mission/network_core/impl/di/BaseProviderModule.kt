package ru.kyamshanov.mission.network_core.impl.di

import dagger.Provides
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.session_front.api.SessionInfo
import ru.kyamshanov.mission.session_front.api.di.SessionFrontComponent

@dagger.Module
internal class BaseProviderModule {

    @Provides
    @ComponentItem
    fun provideSessionInfo(): SessionInfo =
        requireNotNull(Di.getComponent<SessionFrontComponent>()).sessionInfo
}