package ru.kyamshanov.mission.main_screen_feature.impl.di

import dagger.Component
import ru.kyamshanov.mission.creating_project.api.di.CreatingProjectComponent
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.main_screen_feature.api.di.MainScreenComponent
import ru.kyamshanov.mission.main_screen_feature.impl.ui.viewmodel.NavigationBarViewModel
import ru.kyamshanov.mission.navigation_core.api.di.NavigationComponent
import ru.kyamshanov.mission.profile.api.di.ProfileComponent

@Component(
    dependencies = [
        NavigationComponent::class,
        ProfileComponent::class,
        CreatingProjectComponent::class
    ],
    modules = [
        BindsModule::class
    ]
)
@ComponentItem
internal interface ModuleComponent : MainScreenComponent {

    val navigationBarViewModel: NavigationBarViewModel

    @Component.Factory
    interface Factory {

        fun create(
            navigationComponent: NavigationComponent,
            profileComponent: ProfileComponent,
            creatingProjectComponent: CreatingProjectComponent,
        ): ModuleComponent
    }
}