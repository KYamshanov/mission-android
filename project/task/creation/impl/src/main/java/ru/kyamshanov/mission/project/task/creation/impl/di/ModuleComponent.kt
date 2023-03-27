package ru.kyamshanov.mission.project.task.creation.impl.di

import dagger.Component
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.navigation_core.api.di.NavigationComponent
import ru.kyamshanov.mission.network_core.api.di.NetworkComponent
import ru.kyamshanov.mission.project.task.creation.api.di.ProjectTaskCreationComponent
import ru.kyamshanov.mission.project.task.creation.impl.ui.viewmodel.ViewModelProvider
import ru.kyamshanov.mission.time.api.DateFormatterProvider
import ru.kyamshanov.mission.time.di.TimeFormatterModule

@Component(
    dependencies = [
        NavigationComponent::class,
        NetworkComponent::class
    ],
    modules = [
        BindsModule::class,
        TimeFormatterModule::class
    ]
)
@ComponentItem
internal interface ModuleComponent : ProjectTaskCreationComponent {

    val viewModelProvider: ViewModelProvider

    val dateFormatterProvider: DateFormatterProvider

    @Component.Factory
    interface Factory {

        fun create(
            navigationComponent: NavigationComponent,
            networkComponent: NetworkComponent,
        ): ModuleComponent
    }
}