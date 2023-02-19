package ru.kyamshanov.mission.creating_project.impl.di

import dagger.Component
import ru.kyamshanov.mission.creating_project.api.di.CreatingProjectComponent
import ru.kyamshanov.mission.creating_project.impl.domain.OpenFindingUserScreenUseCase
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.finding_user.api.di.FindingUserComponent
import ru.kyamshanov.mission.navigation_core.api.di.NavigationComponent

@Component(
    dependencies = [
        NavigationComponent::class,
        FindingUserComponent::class,
    ],
    modules = [
        BindsModule::class,
    ]
)
@ComponentItem
internal interface ModuleComponent : CreatingProjectComponent {

    val openFindingUserScreenUseCase: OpenFindingUserScreenUseCase

    @Component.Factory
    interface Factory {

        fun create(
            navigationComponent: NavigationComponent,
            findingUserComponent: FindingUserComponent,
        ): ModuleComponent
    }
}