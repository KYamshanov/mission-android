package ru.kyamshanov.mission.creating_project.impl.di

import dagger.Component
import ru.kyamshanov.mission.creating_project.api.di.CreatingProjectComponent
import ru.kyamshanov.mission.creating_project.impl.domain.usecase.OpenFindingUserScreenUseCase
import ru.kyamshanov.mission.creating_project.impl.ui.viewmodel.CreatingProjectViewModel
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.finding_user.api.di.FindingUserComponent
import ru.kyamshanov.mission.navigation_core.api.di.NavigationComponent
import ru.kyamshanov.mission.network_core.api.di.NetworkComponent
import ru.kyamshanov.mission.project.api.di.ProjectComponent

@Component(
    dependencies = [
        NavigationComponent::class,
        FindingUserComponent::class,
        NetworkComponent::class,
        ProjectComponent::class,
    ],
    modules = [
        BindsModule::class,
    ]
)
@ComponentItem
internal interface ModuleComponent : CreatingProjectComponent {

    val openFindingUserScreenUseCase: OpenFindingUserScreenUseCase

    val creatingProjectViewModel: CreatingProjectViewModel

    @Component.Factory
    interface Factory {

        fun create(
            navigationComponent: NavigationComponent,
            findingUserComponent: FindingUserComponent,
            networkComponent: NetworkComponent,
            projectComponent: ProjectComponent,
        ): ModuleComponent
    }
}