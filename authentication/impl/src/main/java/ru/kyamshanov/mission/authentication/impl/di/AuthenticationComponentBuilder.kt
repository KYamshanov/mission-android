package ru.kyamshanov.mission.authentication.impl.di

import ru.kyamshanov.mission.authentication.di.AuthenticationComponent
import ru.kyamshanov.mission.dagger.ComponentBuilder
import ru.kyamshanov.mission.di_dagger.impl.Di

class AuthenticationComponentBuilder : ComponentBuilder<AuthenticationComponent> {

    override fun build(): AuthenticationComponent =
        DaggerModuleComponent.factory()
            .create(
                navigationComponent = requireNotNull(Di.getComponent())
            )
}