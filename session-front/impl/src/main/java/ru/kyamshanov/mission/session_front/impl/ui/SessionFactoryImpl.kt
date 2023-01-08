package ru.kyamshanov.mission.session_front.impl.ui

import ru.kyamshanov.mission.base_core.api.MissionPreferences
import ru.kyamshanov.mission.session_front.api.SessionFactory
import ru.kyamshanov.mission.session_front.api.UserInfo
import ru.kyamshanov.mission.session_front.api.session.Session
import ru.kyamshanov.mission.session_front.impl.SessionInfoImpl
import ru.kyamshanov.mission.session_front.impl.domain.LoginInteractor
import ru.kyamshanov.mission.session_front.impl.domain.model.AccessData
import ru.kyamshanov.mission.session_front.impl.domain.usecase.ObtainLoginFromTokenUseCase
import ru.kyamshanov.mission.session_front.impl.ui.session.LoggedSessionImpl
import javax.inject.Inject

internal class SessionFactoryImpl @Inject constructor(
    private val loginInteractor: LoginInteractor,
    private val missionPreferences: MissionPreferences,
    private val obtainLoginFromTokenUseCase: ObtainLoginFromTokenUseCase,
    private val sessionInfoImpl: SessionInfoImpl
) : SessionFactory {

    override suspend fun newSession(login: String, password: CharSequence): Result<Session> = kotlin.runCatching {
        loginInteractor.login(login, password).getOrThrow().also {
            missionPreferences.saveValue(PREFERENCES_ACCESS_KEY, it.accessToken)
            missionPreferences.saveValue(PREFERENCES_REFRESH_KEY, it.refreshToken)
        }.let { createSession(it) }
            .also { sessionInfoImpl.session = it }
    }

    override suspend fun refreshSession(): Result<Session> = runCatching {
        val refreshToken = missionPreferences.getValue(PREFERENCES_REFRESH_KEY)
            ?: throw NoSuchElementException("Refresh token not found in shared preferences")
        loginInteractor.refresh(refreshToken).getOrThrow().also {
            missionPreferences.saveValue(PREFERENCES_ACCESS_KEY, it.accessToken)
            missionPreferences.saveValue(PREFERENCES_REFRESH_KEY, it.refreshToken)
        }.let { createSession(it) }
            .also { sessionInfoImpl.session = it }
    }

    private fun createSession(data: AccessData) =
        LoggedSessionImpl(
            userInfo = UserInfo(obtainLoginFromTokenUseCase(data.refreshToken)),
            loginInteractor = loginInteractor,
            sessionInfoImpl = SessionInfoImpl(),
            missionPreferences = missionPreferences,
        )
}