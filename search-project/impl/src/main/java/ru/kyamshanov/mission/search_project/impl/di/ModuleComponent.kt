package ru.kyamshanov.mission.search_project.impl.di

import dagger.Component
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.network_core.api.di.NetworkComponent
import ru.kyamshanov.mission.search_project.api.di.SearchProjectFacadeComponent

@Component(
    dependencies = [
        NetworkComponent::class
    ],
    modules = [
        BindsModule::class
    ]
)
@ComponentItem
internal interface ModuleComponent : SearchProjectFacadeComponent {

    @Component.Factory
    interface Factory {

        fun create(
            networkComponent: NetworkComponent,
        ): ModuleComponent
    }
}