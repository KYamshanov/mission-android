package ru.kyamshanov.mission.profile.impl.di

import ru.kyamshanov.mission.dagger.ComponentBuilder
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.profile.api.di.ProfileComponent

class ProfileComponentBuilder : ComponentBuilder<ProfileComponent> {

    override fun build(): ProfileComponent =
        DaggerModuleComponent.factory().create(
            requireNotNull(Di.getComponent()),
            requireNotNull(Di.getComponent()),
            requireNotNull(Di.getComponent()),
            requireNotNull(Di.getComponent()),
        )
}