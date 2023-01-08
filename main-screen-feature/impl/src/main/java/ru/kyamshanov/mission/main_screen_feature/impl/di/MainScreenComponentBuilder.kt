package ru.kyamshanov.mission.main_screen_feature.impl.di

import ru.kyamshanov.mission.dagger.ComponentBuilder
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.main_screen_feature.api.di.MainScreenComponent
import ru.kyamshanov.mission.navigation_core.api.di.NavigationComponent

class MainScreenComponentBuilder() : ComponentBuilder<MainScreenComponent> {

    override fun build(): MainScreenComponent =
        DaggerModuleComponent.factory().create(
            navigationComponent = requireNotNull(Di.getComponent())
        )
}