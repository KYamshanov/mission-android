package ru.kyamshanov.mission.navigation_core.impl.di

import androidx.navigation.NavController
import ru.kyamshanov.mission.dagger.ComponentBuilder
import ru.kyamshanov.mission.navigation_core.api.di.NavigationComponent

class NavigationComponentBuilder(
    private val navController: NavController
) : ComponentBuilder<NavigationComponent> {

    override fun build(): NavigationComponent =
        DaggerModuleComponent.factory().create(navController)
}