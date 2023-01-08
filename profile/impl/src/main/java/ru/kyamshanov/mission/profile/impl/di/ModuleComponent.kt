package ru.kyamshanov.mission.profile.impl.di

import dagger.Component
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.navigation_core.api.di.NavigationComponent
import ru.kyamshanov.mission.profile.api.di.ProfileComponent
import ru.kyamshanov.mission.profile.impl.ui.viewmodel.ProfileViewModel

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

    val profileViewModel: ProfileViewModel

    @Component.Factory
    interface Factory {

        fun create(navigationComponent: NavigationComponent): ModuleComponent
    }
}