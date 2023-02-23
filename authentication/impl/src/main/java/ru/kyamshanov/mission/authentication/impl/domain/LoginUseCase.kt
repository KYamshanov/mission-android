package ru.kyamshanov.mission.authentication.impl.domain

import android.util.Log
import ru.kyamshanov.mission.main_screen_feature.api.navigation.MainScreenLauncher
import ru.kyamshanov.mission.session_front.api.SessionFront
import javax.inject.Inject
import kotlin.jvm.Throws

/**
 * UseCase авторизации
 */
internal interface LoginUseCase {

    @Throws(Exception::class)
    suspend fun login(login: String, password: CharSequence)
}

internal class LoginUseCaseImpl @Inject constructor(
    private val sessionFront: SessionFront,
    private val mainScreenLauncher: MainScreenLauncher
) : LoginUseCase {

    override suspend fun login(login: String, password: CharSequence) {
        sessionFront.newSession(login, password)
            .onSuccess { mainScreenLauncher.launch() }
            .onFailure {
                Log.d(LOG_TAG, "Login went wrong", it)
                throw it
            }
    }

    companion object {

        private const val LOG_TAG = "LoginViewModel"
    }
}