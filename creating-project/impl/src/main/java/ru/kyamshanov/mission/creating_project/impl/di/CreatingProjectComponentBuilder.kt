package ru.kyamshanov.mission.creating_project.impl.di

import ru.kyamshanov.mission.creating_project.api.di.CreatingProjectComponent
import ru.kyamshanov.mission.dagger.ComponentBuilder
import ru.kyamshanov.mission.di_dagger.impl.Di

class CreatingProjectComponentBuilder : ComponentBuilder<CreatingProjectComponent> {

    override fun build(): CreatingProjectComponent =
        DaggerModuleComponent.factory().create(
            navigationComponent = requireNotNull(Di.getComponent()),
            findingUserComponent = requireNotNull(Di.getComponent()),
            networkComponent = requireNotNull(Di.getComponent()),
        )
}