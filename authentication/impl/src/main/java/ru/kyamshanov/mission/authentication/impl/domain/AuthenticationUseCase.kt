package ru.kyamshanov.mission.authentication.impl.domain

import android.util.Log
import ru.kyamshanov.mission.main_screen_feature.api.navigation.MainScreenLauncher
import ru.kyamshanov.mission.session_front.api.di.SessionFrontComponent
import ru.kyamshanov.mission.session_front.api.session.LoggedSession
import javax.inject.Inject
import kotlin.jvm.Throws

internal interface AuthenticationUseCase {

    @Throws(Exception::class)
    suspend fun obtainSession()
}

internal class AuthenticationUseCaseImpl @Inject constructor(
    private val sessionComponent: SessionFrontComponent,
    private val mainScreenLauncher: MainScreenLauncher
) : AuthenticationUseCase {

    override suspend fun obtainSession() {
        if (sessionComponent.sessionInfo is LoggedSession)
            mainScreenLauncher.launch()
        else sessionComponent.sessionFactory.refreshSession()
            .onSuccess {
                mainScreenLauncher.launch()
            }.onFailure {
                Log.d(LOG_TAG, "refresh session failed", it)
                throw it
            }
    }

    companion object {

        private const val LOG_TAG = "AuthenticationViewModel"
    }
}