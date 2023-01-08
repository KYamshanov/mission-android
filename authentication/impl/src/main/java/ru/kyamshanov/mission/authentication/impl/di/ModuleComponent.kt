package ru.kyamshanov.mission.authentication.impl.di

import dagger.Component
import ru.kyamshanov.mission.authentication.di.AuthenticationComponent
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.navigation_core.api.di.NavigationComponent

@Component(
    dependencies = [
        NavigationComponent::class
    ],
    modules = [
        BindsModule::class
    ]
)
@ComponentItem
internal interface ModuleComponent : AuthenticationComponent {

    @Component.Factory
    interface Factory {

        fun create(navigationComponent: NavigationComponent): ModuleComponent
    }
}