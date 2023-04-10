package ru.kyamshanov.mission.task.set_points.impl.di

import ru.kyamshanov.mission.dagger.ComponentBuilder
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.task.set_points.api.di.SetPointsComponent

class SetPointsComponentBuilder : ComponentBuilder<SetPointsComponent> {

    override fun build(): SetPointsComponent =
        DaggerModuleComponent.factory().create(
            navigationComponent = requireNotNull(Di.getComponent()),
            networkComponent = requireNotNull(Di.getComponent()),
        )
}