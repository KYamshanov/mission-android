package ru.kyamshanov.mission.profile_facade.impl.di

import dagger.Component
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.network_core.api.di.NetworkComponent
import ru.kyamshanov.mission.profile_facade.api.di.ProfileFacadeComponent

@Component(
    modules = [
        BindsModule::class,
        ConfigurationModule::class,
        ProvidesModule::class,
    ],
    dependencies = [
        NetworkComponent::class
    ]
)
@ComponentItem
internal interface ModuleComponent : ProfileFacadeComponent {

    @Component.Factory
    interface Factory {

        fun create(
            networkComponent: NetworkComponent,
        ): ModuleComponent
    }
}