package ru.kyamshanov.mission.finding_user.impl.di

import dagger.Component
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.finding_user.api.di.FindingUserComponent
import ru.kyamshanov.mission.finding_user.impl.domain.usecase.ObtainUserUseCase
import ru.kyamshanov.mission.finding_user.impl.domain.usecase.SelectUserUseCase
import ru.kyamshanov.mission.navigation_core.api.di.NavigationComponent

@Component(
    dependencies = [
        NavigationComponent::class
    ],
    modules = [
        BindsModule::class
    ]
)
@ComponentItem
internal interface ModuleComponent : FindingUserComponent {

    val obtainUserUseCase: ObtainUserUseCase

    val selectUserUseCase: SelectUserUseCase

    @Component.Factory
    interface Factory {

        fun create(navigationComponent: NavigationComponent): ModuleComponent
    }
}