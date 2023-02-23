package ru.kyamshanov.mission.navigation_core.impl.di

import dagger.Binds
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.navigation_core.api.Navigator
import ru.kyamshanov.mission.navigation_core.api.ResultProvider
import ru.kyamshanov.mission.navigation_core.impl.NavigatorImpl
import ru.kyamshanov.mission.navigation_core.impl.ResultProviderImpl

@dagger.Module
internal interface BindsModule {

    @Binds
    @ComponentItem
    fun bindNavigator(impl: NavigatorImpl): Navigator

    @Binds
    @ComponentItem
    fun ResultProviderImpl.binResultProvider() : ResultProvider
}