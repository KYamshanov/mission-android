package ru.kyamshanov.mission.navigation_core.impl.di

import androidx.navigation.NavController
import dagger.Provides
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem

@dagger.Module
internal class ProvidesModule {

    @Provides
    @ComponentItem
    fun provideNavigatorControllerHolder(): NavigatorControllerHolder =
        object : NavigatorControllerHolder {
            override var navigator: NavController? = null
        }
}