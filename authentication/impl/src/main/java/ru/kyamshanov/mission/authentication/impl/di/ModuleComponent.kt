package ru.kyamshanov.mission.authentication.impl.di

import dagger.Component
import ru.kyamshanov.mission.authentication.di.AuthenticationComponent
import ru.kyamshanov.mission.authentication.impl.ui.viewmodel.AuthenticationViewModel
import ru.kyamshanov.mission.authentication.impl.ui.viewmodel.LoginViewModel
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.main_screen_feature.api.di.MainScreenComponent
import ru.kyamshanov.mission.navigation_core.api.di.NavigationComponent
import ru.kyamshanov.mission.profile_facade.api.di.ProfileFacadeComponent
import ru.kyamshanov.mission.session_front.api.di.SessionFrontComponent

@Component(
    dependencies = [
        NavigationComponent::class,
        SessionFrontComponent::class,
        MainScreenComponent::class,
        ProfileFacadeComponent::class
    ],
    modules = [
        BindsModule::class
    ]
)
@ComponentItem
internal interface ModuleComponent : AuthenticationComponent {

    val authenticationViewModel: AuthenticationViewModel

    val loginViewModel: LoginViewModel

    @Component.Factory
    interface Factory {

        fun create(
            navigationComponent: NavigationComponent,
            sessionFrontComponent: SessionFrontComponent,
            mainScreenComponent: MainScreenComponent,
            profileFacadeComponent: ProfileFacadeComponent,
        ): ModuleComponent
    }
}