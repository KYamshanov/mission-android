package ru.kyamshanov.mission.project_view.impl.di

import ru.kyamshanov.mission.dagger.ComponentBuilder
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.project_view.api.di.ProjectComponent

class ProjectComponentBuilder : ComponentBuilder<ProjectComponent> {

    override fun build(): ProjectComponent =
        DaggerModuleComponent.factory().create(
            requireNotNull(Di.getComponent()),
            requireNotNull(Di.getComponent()),
            requireNotNull(Di.getComponent()),
            requireNotNull(Di.getComponent()),
            requireNotNull(Di.getComponent()),
            requireNotNull(Di.getComponent()),
        )
}