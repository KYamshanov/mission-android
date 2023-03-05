package ru.kyamshanov.mission.background_registration.impl.di

import dagger.Component
import ru.kyamshanov.mission.background_registration.api.di.BackgroundRegistrationComponent
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
internal interface ModuleComponent : BackgroundRegistrationComponent {

    @Component.Factory
    interface Factory {

        fun create(navigationComponent: NavigationComponent): ModuleComponent
    }
}