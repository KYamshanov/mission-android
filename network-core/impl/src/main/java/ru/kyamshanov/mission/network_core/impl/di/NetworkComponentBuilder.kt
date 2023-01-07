package ru.kyamshanov.mission.network_core.impl.di

import ru.kyamshanov.mission.dagger.ComponentBuilder
import ru.kyamshanov.mission.network_core.api.di.NetworkComponent

class NetworkComponentBuilder : ComponentBuilder<NetworkComponent> {

    override fun build(): NetworkComponent =
        DaggerModuleComponent.factory()
            .create()
}