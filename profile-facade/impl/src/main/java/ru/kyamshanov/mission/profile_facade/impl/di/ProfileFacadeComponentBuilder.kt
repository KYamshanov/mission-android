package ru.kyamshanov.mission.profile_facade.impl.di

import ru.kyamshanov.mission.dagger.ComponentBuilder
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.profile_facade.api.di.ProfileFacadeComponent

class ProfileFacadeComponentBuilder : ComponentBuilder<ProfileFacadeComponent> {

    override fun build(): ProfileFacadeComponent =
        DaggerModuleComponent.factory().create(
            networkComponent = requireNotNull(Di.getComponent()),
        )
}