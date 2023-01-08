package ru.kyamshanov.mission.main_screen_feature.impl.di

import dagger.Component
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.main_screen_feature.api.di.MainScreenComponent
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
internal interface ModuleComponent : MainScreenComponent {

    @Component.Factory
    interface Factory {

        fun create(navigationComponent: NavigationComponent): ModuleComponent
    }
}