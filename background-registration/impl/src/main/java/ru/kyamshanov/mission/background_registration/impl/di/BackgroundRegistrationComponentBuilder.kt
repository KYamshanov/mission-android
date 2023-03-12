package ru.kyamshanov.mission.background_registration.impl.di

import ru.kyamshanov.mission.background_registration.api.di.BackgroundRegistrationComponent
import ru.kyamshanov.mission.dagger.ComponentBuilder
import ru.kyamshanov.mission.di_dagger.impl.Di

class BackgroundRegistrationComponentBuilder : ComponentBuilder<BackgroundRegistrationComponent> {

    override fun build(): BackgroundRegistrationComponent =
        DaggerModuleComponent.factory().create(
            navigationComponent = requireNotNull(Di.getComponent()),
            networkComponent = requireNotNull(Di.getComponent()),
            profileFacadeComponent = requireNotNull(Di.getComponent()),
        )
}