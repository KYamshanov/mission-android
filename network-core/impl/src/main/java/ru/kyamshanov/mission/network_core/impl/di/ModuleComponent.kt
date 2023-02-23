package ru.kyamshanov.mission.network_core.impl.di

import dagger.Component
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.network_core.api.di.NetworkComponent

@Component(
    modules = [
        BindsModule::class,
        BaseProviderModule::class
    ],
)
@ComponentItem
internal interface ModuleComponent : NetworkComponent {

    @Component.Factory
    interface Factory {

        fun create(): ModuleComponent
    }
}