package ru.kyamshanov.mission.task.view.impl.di

import dagger.Component
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.finding_user.api.di.FindingUserComponent
import ru.kyamshanov.mission.navigation_core.api.di.NavigationComponent
import ru.kyamshanov.mission.network_core.api.di.NetworkComponent
import ru.kyamshanov.mission.session_front.api.di.SessionFrontComponent
import ru.kyamshanov.mission.task.set_points.api.di.SetPointsComponent
import ru.kyamshanov.mission.task.view.api.di.TaskViewComponent
import ru.kyamshanov.mission.time.di.TimeFormatterModule

@Component(
    dependencies = [
        NavigationComponent::class,
        NetworkComponent::class,
        SetPointsComponent::class,
        FindingUserComponent::class,
        SessionFrontComponent::class,
    ],
    modules = [
        BindsModule::class,
        TimeFormatterModule::class,
    ]
)
@ComponentItem
internal interface ModuleComponent : TaskViewComponent {

    val viewModelSupplier: ViewModelSupplier

    val taskViewModelFactory: TaskViewModelFactory

    val subtaskViewModelFactory: SubtaskViewModelFactory

    @Component.Factory
    interface Factory {

        fun create(
            navigationComponent: NavigationComponent,
            networkComponent: NetworkComponent,
            setPointsComponent: SetPointsComponent,
            findingUserComponent: FindingUserComponent,
            sessionFrontComponent: SessionFrontComponent,
        ): ModuleComponent
    }
}