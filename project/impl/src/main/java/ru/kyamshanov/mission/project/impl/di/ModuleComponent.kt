package ru.kyamshanov.mission.project.impl.di

import dagger.Component
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.navigation_core.api.di.NavigationComponent
import ru.kyamshanov.mission.project.api.di.ProjectComponent

@Component(
    dependencies = [
        NavigationComponent::class
    ],
    modules = [
        BindsModule::class
    ]
)
@ComponentItem
internal interface ModuleComponent : ProjectComponent {

    @Component.Factory
    interface Factory {

        fun create(navigationComponent: NavigationComponent): ModuleComponent
    }
}