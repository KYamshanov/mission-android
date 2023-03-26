package ru.kyamshanov.mission.project_view.impl.di

import dagger.Component
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.navigation_core.api.di.NavigationComponent
import ru.kyamshanov.mission.network_core.api.di.NetworkComponent
import ru.kyamshanov.mission.project.task.creation.api.di.ProjectTaskCreationComponent
import ru.kyamshanov.mission.project_view.api.di.ProjectComponent
import ru.kyamshanov.mission.project_view.impl.ui.viewmodel.ViewModelProvider
import ru.kyamshanov.mission.session_front.api.di.SessionFrontComponent

@Component(
    dependencies = [
        NavigationComponent::class,
        NetworkComponent::class,
        SessionFrontComponent::class,
        ProjectTaskCreationComponent::class,
    ],
    modules = [
        BindsModule::class
    ]
)
@ComponentItem
internal interface ModuleComponent : ProjectComponent {

    val viewModelProvider: ViewModelProvider

    @Component.Factory
    interface Factory {

        fun create(
            navigationComponent: NavigationComponent,
            networkComponent: NetworkComponent,
            sessionFrontComponent: SessionFrontComponent,
            projectTaskCreationComponent: ProjectTaskCreationComponent,
        ): ModuleComponent
    }
}