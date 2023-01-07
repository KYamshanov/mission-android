package ru.kyamshanov.mission.session_front.impl.di

import dagger.Binds
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.session_front.api.SessionFactory
import ru.kyamshanov.mission.session_front.api.SessionInfo
import ru.kyamshanov.mission.session_front.impl.SessionInfoImpl
import ru.kyamshanov.mission.session_front.impl.data.LoginInteractorImpl
import ru.kyamshanov.mission.session_front.impl.data.api.AuthenticationApi
import ru.kyamshanov.mission.session_front.impl.data.api.AuthenticationApiImpl
import ru.kyamshanov.mission.session_front.impl.domain.LoginInteractor
import ru.kyamshanov.mission.session_front.impl.ui.SessionFactoryImpl

@dagger.Module
internal interface BindsModule {

    @Binds
    @ComponentItem
    fun bindSessionInfo(impl: SessionInfoImpl): SessionInfo

    @Binds
    @ComponentItem
    fun bindAuthenticationApi(impl: AuthenticationApiImpl): AuthenticationApi

    @Binds
    @ComponentItem
    fun bindSessionFactory(impl: SessionFactoryImpl): SessionFactory

    @Binds
    @ComponentItem
    fun bindLoginInteractor(impl: LoginInteractorImpl): LoginInteractor
}