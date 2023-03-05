package ru.kyamshanov.mission.session_front.impl.di

import dagger.Binds
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.session_front.api.SessionFront
import ru.kyamshanov.mission.session_front.api.SessionInfo
import ru.kyamshanov.mission.session_front.impl.SessionInfoImpl
import ru.kyamshanov.mission.session_front.impl.data.JwtLoginInteractorImpl
import ru.kyamshanov.mission.session_front.impl.data.JwtTokenInteractorImpl
import ru.kyamshanov.mission.session_front.impl.data.api.AuthenticationApi
import ru.kyamshanov.mission.session_front.impl.data.api.AuthenticationApiImpl
import ru.kyamshanov.mission.session_front.impl.domain.JwtLoginInteractor
import ru.kyamshanov.mission.session_front.impl.domain.JwtTokenInteractor
import ru.kyamshanov.mission.session_front.impl.ui.SessionFrontImpl

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
    fun bindSessionFactory(impl: SessionFrontImpl): SessionFront

    @Binds
    @ComponentItem
    fun bindLoginInteractor(impl: JwtLoginInteractorImpl): JwtLoginInteractor

    @Binds
    @ComponentItem
    fun bindJwtTokenInteractor(impl: JwtTokenInteractorImpl): JwtTokenInteractor
}