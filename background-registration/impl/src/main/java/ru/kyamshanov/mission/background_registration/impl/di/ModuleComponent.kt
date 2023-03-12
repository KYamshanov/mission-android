package ru.kyamshanov.mission.background_registration.impl.di

import dagger.Component
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.navigation_core.api.di.NavigationComponent
import ru.kyamshanov.mission.network_core.api.di.NetworkComponent
import ru.kyamshanov.mission.profile_facade.api.di.ProfileFacadeComponent

@Component(
    dependencies = [
        NavigationComponent::class,
        NetworkComponent::class,
        ProfileFacadeComponent::class
    ],
    modules = [
        BindsModule::class
    ]
)
@ComponentItem
internal interface ModuleComponent : InternalComponentInterface {

    @Component.Factory
    interface Factory {

        fun create(
            navigationComponent: NavigationComponent,
            networkComponent: NetworkComponent,
            profileFacadeComponent : ProfileFacadeComponent
        ): ModuleComponent
    }
}