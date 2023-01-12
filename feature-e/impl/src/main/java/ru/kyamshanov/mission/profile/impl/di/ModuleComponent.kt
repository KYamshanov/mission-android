package ru.kyamshanov.mission.profile.impl.di

import dagger.Component
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.navigation_core.api.di.NavigationComponent
import ru.kyamshanov.mission.profile.api.di.ProfileComponent

@Component(
    dependencies = [
        NavigationComponent::class
    ],
    modules = [
        BindsModule::class
    ]
)
@ComponentItem
internal interface ModuleComponent : ProfileComponent {

    @Component.Factory
    interface Factory {

        fun create(navigationComponent: NavigationComponent): ModuleComponent
    }
}