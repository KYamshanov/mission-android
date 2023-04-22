package ru.kyamshanov.mission.task.view.impl.di

import ru.kyamshanov.mission.dagger.ComponentBuilder
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.task.view.api.di.TaskViewComponent

class TaskViewComponentBuilder : ComponentBuilder<TaskViewComponent> {

    override fun build(): TaskViewComponent =
        DaggerModuleComponent.factory().create(
            requireNotNull(Di.getComponent()),
            requireNotNull(Di.getComponent()),
            requireNotNull(Di.getComponent()),
            requireNotNull(Di.getComponent()),
            requireNotNull(Di.getComponent()),
        )
}