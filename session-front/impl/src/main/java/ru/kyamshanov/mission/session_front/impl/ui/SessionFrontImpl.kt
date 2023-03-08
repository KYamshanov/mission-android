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
import ru.kyamshanov.mission.profile_facade.api.domain.interactor.VerifyingProfileInteractor
import ru.kyamshanov.mission.session_front.api.SessionFront
import ru.kyamshanov.mission.session_front.api.UserInfo
import ru.kyamshanov.mission.session_front.api.model.UserRole
import ru.kyamshanov.mission.session_front.api.session.JwtLoggedSession
import ru.kyamshanov.mission.session_front.api.session.LoggedSession
import ru.kyamshanov.mission.session_front.api.session.Session
import ru.kyamshanov.mission.session_front.api.session.UnauthorizedSession
import ru.kyamshanov.mission.session_front.impl.SessionInfoImpl
import ru.kyamshanov.mission.session_front.impl.domain.JwtLoginInteractor
import ru.kyamshanov.mission.session_front.impl.domain.JwtTokenInteractor
import ru.kyamshanov.mission.session_front.impl.domain.model.AccessData
import ru.kyamshanov.mission.session_front.impl.domain.usecase.IdentifyUserUseCase
import ru.kyamshanov.mission.session_front.impl.ui.session.JwtLoggedSessionImpl
import ru.kyamshanov.mission.session_front.impl.ui.session.LoggedSessionImpl

internal class SessionFrontImpl @Inject constructor(
    private val jwtLoginInteractor: JwtLoginInteractor,
    private val missionPreferences: MissionPreferences,
    private val jwtTokenInteractor: JwtTokenInteractor,
    private val sessionInfoImpl: SessionInfoImpl,
    private val identifyUserUseCase: IdentifyUserUseCase,
    private val verifyingProfileInteractor: VerifyingProfileInteractor,
) : SessionFront {

    private var sessionLifecycleScope: CoroutineScope? = null
        set(value) {
            field?.cancel()
            field = value
        }

    init {
        CoroutineScope(Job()).launch {
            refreshSession()
                .onSuccess {
                    verifyingProfileInteractor.completeProfile()
                    startAutoRefreshing()
                }
                .onFailure { makeUnauthorizedSession(it) }
            cancel()
        }
    }

    override suspend fun openSession(login: String, password: CharSequence): Result<Session> = kotlin.runCatching {
        jwtLoginInteractor.login(login, password).getOrThrow().also {
            missionPreferences.saveValue(PREFERENCES_ACCESS_KEY, it.accessToken)
            missionPreferences.saveValue(PREFERENCES_REFRESH_KEY, it.refreshToken)
            missionPreferences.saveValue(PREFERENCES_SESSION_LOGIN_KEY, login)
        }.let { createJwtSession(it) }
            .also { sessionInfoImpl.session = it }
            .let { finishSetupSession(login, it) }
            .also { verifyingProfileInteractor.completeProfile() }
            .also { startAutoRefreshing() }
    }

    override suspend fun destroySession() {
        sessionLifecycleScope = null
        makeUnauthorizedSession(IllegalStateException("The session is destroyed"))

        missionPreferences.getValue(PREFERENCES_REFRESH_KEY)
            ?.let { jwtLoginInteractor.blockRefresh(it) }

        missionPreferences.remove(PREFERENCES_ACCESS_KEY)
        missionPreferences.remove(PREFERENCES_REFRESH_KEY)
        missionPreferences.remove(PREFERENCES_SESSION_LOGIN_KEY)

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
        var mRefreshToken: String? = null
        var mLogin: String? = null

        (sessionInfoImpl.session as? LoggedSessionImpl)?.let {
            mRefreshToken = it.refreshToken
            mLogin = it.userInfo.login
        } ?: run {
            mRefreshToken = missionPreferences.getValue(PREFERENCES_REFRESH_KEY)
            mLogin = missionPreferences.getValue(PREFERENCES_SESSION_LOGIN_KEY)
        }

        val refreshToken = requireNotNull(mRefreshToken) { "Refresh token has not found in shared preferences" }
        val login = requireNotNull(mLogin) { "Login has not found in shared preferences" }

        jwtLoginInteractor.refresh(refreshToken).getOrThrow().also {
            missionPreferences.saveValue(PREFERENCES_ACCESS_KEY, it.accessToken)
            missionPreferences.saveValue(PREFERENCES_REFRESH_KEY, it.refreshToken)
        }.let { createJwtSession(it) }
            .also { sessionInfoImpl.session = it }
            .let { finishSetupSession(login, it) }
    }

    private fun createJwtSession(data: AccessData): JwtLoggedSession = JwtLoggedSessionImpl(
        accessToken = data.accessToken,
        refreshToken = data.refreshToken,
    )

    private suspend fun finishSetupSession(login: String, jwtLoggedSession: JwtLoggedSession): LoggedSession =
        jwtTokenInteractor.parse(jwtLoggedSession.refreshToken).let { jwtModel ->
            UserInfo(
                login = login,
                roles = jwtModel.roles.map { UserRole.valueOf(it) }
            )
        }.let { userInfo ->
            val idToken = identifyUserUseCase.identify().getOrThrow()
            LoggedSessionImpl(
                userInfo = userInfo,
                idToken = idToken,
                jwtLoggedSession = jwtLoggedSession,
                jwtLoginInteractor = jwtLoginInteractor
            )
        }.also { sessionInfoImpl.session = it }
}