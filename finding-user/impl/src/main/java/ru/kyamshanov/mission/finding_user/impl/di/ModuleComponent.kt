package ru.kyamshanov.mission.finding_user.impl.di

import dagger.Component
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.finding_user.api.di.FindingUserComponent
import ru.kyamshanov.mission.finding_user.impl.domain.usecase.ObtainUserUseCaseFactory
import ru.kyamshanov.mission.finding_user.impl.domain.usecase.SelectUserUseCase
import ru.kyamshanov.mission.navigation_core.api.Navigator
import ru.kyamshanov.mission.navigation_core.api.di.NavigationComponent
import ru.kyamshanov.mission.network_core.api.di.NetworkComponent
import ru.kyamshanov.mission.session_front.api.di.SessionFrontComponent

@Component(
    dependencies = [
        NavigationComponent::class,
        NetworkComponent::class,
        SessionFrontComponent::class
    ],
    modules = [
        BindsModule::class
    ]
)
@ComponentItem
internal interface ModuleComponent : FindingUserComponent {

    val obtainUserUseCaseFactory: ObtainUserUseCaseFactory

    val selectUserUseCase: SelectUserUseCase

    val navigator : Navigator

    @Component.Factory
    interface Factory {

        fun create(
            navigationComponent: NavigationComponent,
            networkComponent: NetworkComponent,
            sessionFrontComponent: SessionFrontComponent,
        ): ModuleComponent
    }
}