package ru.kyamshanov.mission.navigation_core.impl.di

import dagger.Component
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.navigation_core.api.di.NavigationComponent

@Component(
    modules = [
        BindsModule::class,
        ProvidesModule::class,
    ]
)
@ComponentItem
interface ModuleComponent : NavigationComponent {

    val navigatorControllerHolder: NavigatorControllerHolder

    @Component.Factory
    interface Factory {

        fun create(): ModuleComponent
    }
}