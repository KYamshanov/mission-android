package ru.kyamshanov.mission.network_core.impl.di

import dagger.Binds
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.network_core.api.RequestFactory
import ru.kyamshanov.mission.network_core.impl.RequestFactoryImpl

@dagger.Module
internal interface BindsModule {

    @Binds
    @ComponentItem
    fun bindSessionInfo(impl: RequestFactoryImpl): RequestFactory
}