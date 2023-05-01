package ru.kyamshanov.mission.profile.impl.di

import dagger.Component
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.navigation_core.api.di.NavigationComponent
import ru.kyamshanov.mission.network_core.api.di.NetworkComponent
import ru.kyamshanov.mission.profile.api.di.ProfileComponent
import ru.kyamshanov.mission.profile.impl.ui.viewmodel.ProfileViewModel
import ru.kyamshanov.mission.profile_facade.api.di.ProfileFacadeComponent
import ru.kyamshanov.mission.session_front.api.di.SessionFrontComponent

@Component(
    dependencies = [
        NavigationComponent::class,
        SessionFrontComponent::class,
        ProfileFacadeComponent::class,
        NetworkComponent::class
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

        fun create(
            navigationComponent: NavigationComponent,
            sessionFrontComponent: SessionFrontComponent,
            profileFacadeComponent: ProfileFacadeComponent,
            networkComponent: NetworkComponent,
        ): ModuleComponent
    }
}