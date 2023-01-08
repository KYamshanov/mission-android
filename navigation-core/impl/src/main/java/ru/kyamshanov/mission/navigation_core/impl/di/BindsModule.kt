package ru.kyamshanov.mission.navigation_core.impl.di

import dagger.Binds
import ru.kyamshanov.mission.navigation_core.api.Navigator
import ru.kyamshanov.mission.navigation_core.impl.NavigatorImpl

@dagger.Module
internal interface BindsModule {

    @Binds
    fun bindNavigator(impl: NavigatorImpl): Navigator
}