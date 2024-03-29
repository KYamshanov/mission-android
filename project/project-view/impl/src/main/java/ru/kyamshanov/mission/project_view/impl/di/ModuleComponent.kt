package ru.kyamshanov.mission.project_view.impl.di

import dagger.Component
import ru.kyamshanov.mission.base_core.api.di.BaseCoreComponent
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.finding_user.api.di.FindingUserComponent
import ru.kyamshanov.mission.navigation_core.api.di.NavigationComponent
import ru.kyamshanov.mission.network_core.api.di.NetworkComponent
import ru.kyamshanov.mission.project.task.creation.api.di.ProjectTaskCreationComponent
import ru.kyamshanov.mission.project_view.api.di.ProjectComponent
import ru.kyamshanov.mission.project_view.impl.domain.usecase.TaskStagePresentUseCase
import ru.kyamshanov.mission.session_front.api.di.SessionFrontComponent
import ru.kyamshanov.mission.time.di.TimeFormatterModule

@Component(
    dependencies = [
        NavigationComponent::class,
        NetworkComponent::class,
        SessionFrontComponent::class,
        ProjectTaskCreationComponent::class,
        BaseCoreComponent::class,
        FindingUserComponent::class,
    ],
    modules = [
        BindsModule::class,
        TimeFormatterModule::class,
        ProvidesModule::class,
    ]
)
@ComponentItem
internal interface ModuleComponent : ProjectComponent {

    val projectViewModelFactory: ProjectViewModelFactory

    val totalPointsViewModelFactory: TotalPointsViewModelFactory

    val taskStagePresentUseCase: TaskStagePresentUseCase

    val participantsListViewModelFactory: ParticipantsListViewModelFactory

    @Component.Factory
    interface Factory {

        fun create(
            navigationComponent: NavigationComponent,
            networkComponent: NetworkComponent,
            sessionFrontComponent: SessionFrontComponent,
            projectTaskCreationComponent: ProjectTaskCreationComponent,
            baseCoreComponent: BaseCoreComponent,
            findingUserComponent: FindingUserComponent,
        ): ModuleComponent
    }
}