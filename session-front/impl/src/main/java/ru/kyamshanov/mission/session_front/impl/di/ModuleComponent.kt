package ru.kyamshanov.mission.session_front.impl.di

import dagger.Component
import ru.kyamshanov.mission.base_core.api.di.BaseCoreComponent
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.network_core.api.di.NetworkComponent
import ru.kyamshanov.mission.profile_facade.api.di.ProfileFacadeComponent
import ru.kyamshanov.mission.session_front.api.di.SessionFrontComponent

@Component(
    dependencies = [
        BaseCoreComponent::class,
        NetworkComponent::class,
        ProfileFacadeComponent::class
    ],
    modules = [
        BindsModule::class
    ]
)
@ComponentItem
internal interface ModuleComponent : SessionFrontComponent {

    @Component.Factory
    interface Factory {

        fun create(
            baseCoreComponent: BaseCoreComponent,
            networkComponent: NetworkComponent,
            profileFacadeComponent: ProfileFacadeComponent,
        ): ModuleComponent
    }
}