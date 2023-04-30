package ru.kyamshanov.mission.finding_user.impl.di

import ru.kyamshanov.mission.dagger.ComponentBuilder
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.finding_user.api.di.FindingUserComponent

class FindingUserComponentBuilder : ComponentBuilder<FindingUserComponent> {

    override fun build(): FindingUserComponent =
        DaggerModuleComponent.factory().create(
            requireNotNull(Di.getComponent()),
            requireNotNull(Di.getComponent()),
            requireNotNull(Di.getComponent()),
        )
}