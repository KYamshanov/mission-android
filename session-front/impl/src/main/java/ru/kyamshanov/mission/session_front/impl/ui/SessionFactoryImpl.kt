package ru.kyamshanov.mission.session_front.impl.ui

import ru.kyamshanov.mission.base_core.api.MissionPreferences
import ru.kyamshanov.mission.session_front.api.SessionFactory
import ru.kyamshanov.mission.session_front.api.UserInfo
import ru.kyamshanov.mission.session_front.api.session.Session
import ru.kyamshanov.mission.session_front.api.session.UnidentifiedSession
import ru.kyamshanov.mission.session_front.impl.SessionInfoImpl
import ru.kyamshanov.mission.session_front.impl.domain.LoginInteractor
import ru.kyamshanov.mission.session_front.impl.domain.session.LoggedSessionImpl
import ru.kyamshanov.mission.session_front.impl.domain.usecase.ObtainLoginFromTokenUseCase
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
        }.let { LoggedSessionImpl(UserInfo(login)) }
            .also { sessionInfoImpl.session = it }
    }

    override suspend fun refreshSession(): Result<Session> = runCatching {
        val refreshToken = missionPreferences.getValue(PREFERENCES_REFRESH_KEY)
            ?: throw NoSuchElementException("Refresh token not found in shared preferences")
        loginInteractor.refresh(refreshToken).getOrThrow().also {
            missionPreferences.saveValue(PREFERENCES_ACCESS_KEY, it.accessToken)
            missionPreferences.saveValue(PREFERENCES_REFRESH_KEY, it.refreshToken)
        }.let { LoggedSessionImpl(UserInfo(obtainLoginFromTokenUseCase(it.refreshToken))) }
            .also { sessionInfoImpl.session = it }
    }

    override suspend fun destroySession() {
        missionPreferences.getValue(PREFERENCES_REFRESH_KEY)
            ?.let { loginInteractor.blockRefresh(it) }

        missionPreferences.remove(PREFERENCES_ACCESS_KEY)
        missionPreferences.remove(PREFERENCES_REFRESH_KEY)
        sessionInfoImpl.session = UnidentifiedSession
    }
}