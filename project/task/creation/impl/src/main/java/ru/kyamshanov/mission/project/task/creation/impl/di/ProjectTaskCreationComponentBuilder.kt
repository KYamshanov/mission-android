package ru.kyamshanov.mission.project.task.creation.impl.di

import ru.kyamshanov.mission.dagger.ComponentBuilder
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.project.task.creation.api.di.ProjectTaskCreationComponent

class ProjectTaskCreationComponentBuilder : ComponentBuilder<ProjectTaskCreationComponent> {

    override fun build(): ProjectTaskCreationComponent =
        DaggerModuleComponent.factory().create(
            navigationComponent = requireNotNull(Di.getComponent()),
            networkComponent = requireNotNull(Di.getComponent()),
        )
}