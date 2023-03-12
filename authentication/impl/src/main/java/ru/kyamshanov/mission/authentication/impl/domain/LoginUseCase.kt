package ru.kyamshanov.mission.authentication.impl.domain

import android.util.Log
import javax.inject.Inject
import ru.kyamshanov.mission.main_screen_feature.api.navigation.MainScreenLauncher
import ru.kyamshanov.mission.profile_facade.api.domain.interactor.VerifyingProfileInteractor
import ru.kyamshanov.mission.session_front.api.SessionFront

/**
 * UseCase авторизации
 */
internal interface LoginUseCase {

    @Throws(Exception::class)
    suspend fun login(login: String, password: CharSequence)
}

internal class LoginUseCaseImpl @Inject constructor(
    private val sessionFront: SessionFront,
    private val mainScreenLauncher: MainScreenLauncher,
    private val verifyingProfileInteractor: VerifyingProfileInteractor,
) : LoginUseCase {

    override suspend fun login(login: String, password: CharSequence) {
        sessionFront.openSession(login, password)
            .onSuccess {
                mainScreenLauncher.launch()
                verifyingProfileInteractor.completeProfile(fetchProfile = true)
            }
            .onFailure {
                Log.d(LOG_TAG, "Login went wrong", it)
                throw it
            }
    }

    companion object {

        private const val LOG_TAG = "LoginViewModel"
    }
}