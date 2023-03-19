package ru.kyamshanov.mission.project.impl.di

import ru.kyamshanov.mission.dagger.ComponentBuilder
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.project.api.di.ProjectComponent

class ProjectComponentBuilder : ComponentBuilder<ProjectComponent> {

    override fun build(): ProjectComponent =
        DaggerModuleComponent.factory().create(
            navigationComponent = requireNotNull(Di.getComponent()),
            networkComponent = requireNotNull(Di.getComponent()),
            sessionFrontComponent = requireNotNull(Di.getComponent()),
        )
}