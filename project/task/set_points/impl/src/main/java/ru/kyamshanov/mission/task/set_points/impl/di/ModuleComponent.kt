package ru.kyamshanov.mission.task.set_points.impl.di

import dagger.Component
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.navigation_core.api.di.NavigationComponent
import ru.kyamshanov.mission.network_core.api.di.NetworkComponent
import ru.kyamshanov.mission.task.set_points.api.di.SetPointsComponent

@Component(
    dependencies = [
        NavigationComponent::class,
        NetworkComponent::class
    ],
    modules = [
        BindsModule::class
    ]
)
@ComponentItem
internal interface ModuleComponent : SetPointsComponent {

    val viewModelSupplier: ViewModelSupplier

    @Component.Factory
    interface Factory {

        fun create(
            navigationComponent: NavigationComponent,
            networkComponent: NetworkComponent,
        ): ModuleComponent
    }
}