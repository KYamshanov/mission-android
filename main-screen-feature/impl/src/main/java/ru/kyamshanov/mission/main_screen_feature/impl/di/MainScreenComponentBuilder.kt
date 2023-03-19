package ru.kyamshanov.mission.main_screen_feature.impl.di

import ru.kyamshanov.mission.dagger.ComponentBuilder
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.main_screen_feature.api.di.MainScreenComponent

class MainScreenComponentBuilder() : ComponentBuilder<MainScreenComponent> {

    override fun build(): MainScreenComponent =
        DaggerModuleComponent.factory().create(
            navigationComponent = requireNotNull(Di.getComponent()),
            profileComponent = requireNotNull(Di.getComponent()),
            creatingProjectComponent = requireNotNull(Di.getComponent()),
            searchProjectFacadeComponent = requireNotNull(Di.getComponent()),
            projectComponent = requireNotNull(Di.getComponent()),
        )
}