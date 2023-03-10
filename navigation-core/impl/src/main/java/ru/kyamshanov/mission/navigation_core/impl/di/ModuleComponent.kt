package ru.kyamshanov.mission.navigation_core.impl.di

import androidx.navigation.NavController
import dagger.BindsInstance
import dagger.Component
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.navigation_core.api.di.NavigationComponent

@Component(modules = [BindsModule::class])
@ComponentItem
internal interface ModuleComponent : NavigationComponent {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance navController: NavController): ModuleComponent
    }
}