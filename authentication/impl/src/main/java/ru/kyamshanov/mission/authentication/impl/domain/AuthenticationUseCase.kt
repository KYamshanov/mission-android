package ru.kyamshanov.mission.authentication.impl.domain

import android.util.Log
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.kyamshanov.mission.main_screen_feature.api.navigation.MainScreenLauncher
import ru.kyamshanov.mission.profile_facade.api.domain.interactor.VerifyingProfileInteractor
import ru.kyamshanov.mission.session_front.api.di.SessionFrontComponent
import ru.kyamshanov.mission.session_front.api.session.LoggedSession
import ru.kyamshanov.mission.session_front.api.session.Session
import ru.kyamshanov.mission.session_front.api.session.UnauthorizedSession

internal interface AuthenticationUseCase {

    suspend fun obtainSession(): Result<Unit>
}

internal class AuthenticationUseCaseImpl @Inject constructor(
    private val sessionComponent: SessionFrontComponent,
    private val mainScreenLauncher: MainScreenLauncher,
    private val verifyingProfileInteractor: VerifyingProfileInteractor,
) : AuthenticationUseCase {

    override suspend fun obtainSession() = runCatching {
        if (sessionComponent.sessionInfo is LoggedSession) {
            withContext(Dispatchers.Main) { mainScreenLauncher.launch() }
            verifyingProfileInteractor.completeProfile(fetchProfile = false)
        } else awaitRefreshSession()
    }

    private suspend fun awaitRefreshSession() = coroutineScope {
        launch {
            sessionComponent.sessionInfo.sessionState
                .collect { session ->
                    session.handleReceivedSession()
                        .onSuccess { this.cancel() }
                }
        }.join()
    }

    private suspend fun Session.handleReceivedSession(): Result<Unit> = when (this) {
        is UnauthorizedSession -> {
            Log.d(LOG_TAG, "refresh session failed", reason)
            throw reason
        }

        is LoggedSession -> {
            withContext(Dispatchers.Main) { mainScreenLauncher.launch() }
            verifyingProfileInteractor.completeProfile(fetchProfile = false)
            Result.success(Unit)
        }

        else -> Result.failure(IllegalStateException("The session is trying to refresh "))
    }

    companion object {

        private const val LOG_TAG = "AuthenticationViewModel"
    }
}