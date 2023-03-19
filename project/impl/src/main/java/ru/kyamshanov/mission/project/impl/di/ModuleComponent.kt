package ru.kyamshanov.mission.project.impl.di

import dagger.Component
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.navigation_core.api.di.NavigationComponent
import ru.kyamshanov.mission.network_core.api.di.NetworkComponent
import ru.kyamshanov.mission.project.api.di.ProjectComponent
import ru.kyamshanov.mission.project.impl.ui.viewmodel.ViewModelProvider
import ru.kyamshanov.mission.session_front.api.SessionFront
import ru.kyamshanov.mission.session_front.api.di.SessionFrontComponent

@Component(
    dependencies = [
        NavigationComponent::class,
        NetworkComponent::class,
        SessionFrontComponent::class
    ],
    modules = [
        BindsModule::class
    ]
)
@ComponentItem
internal interface ModuleComponent : ProjectComponent, ViewModelProvider {

    @Component.Factory
    interface Factory {

        fun create(
            navigationComponent: NavigationComponent,
            networkComponent: NetworkComponent,
            sessionFrontComponent: SessionFrontComponent,
        ): ModuleComponent
    }
}