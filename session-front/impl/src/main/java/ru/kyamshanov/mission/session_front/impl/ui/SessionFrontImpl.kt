package ru.kyamshanov.mission.session_front.impl.ui

import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import ru.kyamshanov.mission.authentication.di.AuthenticationComponent
import ru.kyamshanov.mission.base_core.api.MissionPreferences
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.session_front.api.SessionFront
import ru.kyamshanov.mission.session_front.api.session.JwtLoggedSession
import ru.kyamshanov.mission.session_front.api.session.Session
import ru.kyamshanov.mission.session_front.api.session.UnauthorizedSession
import ru.kyamshanov.mission.session_front.impl.SessionInfoImpl
import ru.kyamshanov.mission.session_front.impl.domain.JwtTokenInteractor
import ru.kyamshanov.mission.session_front.impl.domain.LoginInteractor
import ru.kyamshanov.mission.session_front.impl.domain.model.AccessData
import ru.kyamshanov.mission.session_front.impl.ui.model.toUserInfo
import ru.kyamshanov.mission.session_front.impl.ui.session.JwtLoggedSessionImpl

internal class SessionFrontImpl @Inject constructor(
    private val loginInteractor: LoginInteractor,
    private val missionPreferences: MissionPreferences,
    private val jwtTokenInteractor: JwtTokenInteractor,
    private val sessionInfoImpl: SessionInfoImpl,
) : SessionFront {

    private var sessionLifecycleScope: CoroutineScope? = null
        set(value) {
            field?.cancel()
            field = value
        }

    init {
        CoroutineScope(Job()).launch {
            refreshSession()
                .onSuccess { startAutoRefreshing() }
                .onFailure { makeUnauthorizedSession(it) }
            cancel()
        }
    }

    override suspend fun openSession(login: String, password: CharSequence): Result<Session> = kotlin.runCatching {
        loginInteractor.login(login, password).getOrThrow().also {
            missionPreferences.saveValue(PREFERENCES_ACCESS_KEY, it.accessToken)
            missionPreferences.saveValue(PREFERENCES_REFRESH_KEY, it.refreshToken)
        }.let { createSession(it) }
            .also { sessionInfoImpl.session = it }
            .also { startAutoRefreshing() }
    }

    override suspend fun destroySession() {
        sessionLifecycleScope = null
        makeUnauthorizedSession(IllegalStateException("The session is destroyed"))

        missionPreferences.getValue(PREFERENCES_REFRESH_KEY)
            ?.let { loginInteractor.blockRefresh(it) }

        missionPreferences.remove(PREFERENCES_ACCESS_KEY)
        missionPreferences.remove(PREFERENCES_REFRESH_KEY)

        Di.getComponent<AuthenticationComponent>()?.launcher?.launch()
    }

    private fun makeUnauthorizedSession(reason: Throwable) {
        sessionInfoImpl.session = UnauthorizedSession(reason)
    }

    private fun startAutoRefreshing() {
        sessionLifecycleScope = CoroutineScope(Job())

        sessionLifecycleScope?.launch {
            while (isActive) {
                delay(3600_000L)
                refreshSession()
                    .onFailure { destroySession() }
            }
        }
    }

    private suspend fun refreshSession(): Result<Session> = runCatching {
        val refreshToken =
            (sessionInfoImpl.session as? JwtLoggedSession)?.refreshToken
                ?: missionPreferences.getValue(PREFERENCES_REFRESH_KEY)
                ?: throw NoSuchElementException("Refresh token not found in shared preferences")
        loginInteractor.refresh(refreshToken).getOrThrow().also {
            missionPreferences.saveValue(PREFERENCES_ACCESS_KEY, it.accessToken)
            missionPreferences.saveValue(PREFERENCES_REFRESH_KEY, it.refreshToken)
        }.let { createSession(it) }
            .also { sessionInfoImpl.session = it }
    }

    private fun createSession(data: AccessData) =
        JwtLoggedSessionImpl(
            userInfo = jwtTokenInteractor.parse(data.refreshToken).toUserInfo(),
            loginInteractor = loginInteractor,
            accessToken = data.accessToken,
            refreshToken = data.refreshToken,
        )
}