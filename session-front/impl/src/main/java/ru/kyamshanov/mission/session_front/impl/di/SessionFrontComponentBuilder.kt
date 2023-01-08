package ru.kyamshanov.mission.session_front.impl.di

import ru.kyamshanov.mission.dagger.ComponentBuilder
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.session_front.api.di.SessionFrontComponent

class SessionFrontComponentBuilder : ComponentBuilder<SessionFrontComponent> {

    override fun build(): SessionFrontComponent =
        DaggerModuleComponent.factory()
            .create(
                baseCoreComponent = requireNotNull(Di.getComponent()),
                networkComponent = requireNotNull(Di.getComponent())
            )
}