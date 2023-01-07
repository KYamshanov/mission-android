package ru.kyamshanov.mission.authentication.impl.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow.DROP_OLDEST
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.kyamshanov.mission.authentication.impl.ui.model.LoginScreenState
import ru.kyamshanov.mission.main_screen_feature.api.navigation.MainScreenLauncher
import ru.kyamshanov.mission.session_front.api.SessionFactory

internal class LoginViewModel(
    private val sessionFactory: SessionFactory,
    private val mainScreenLauncher: MainScreenLauncher
) : ViewModel() {

    private val _screenState =
        MutableSharedFlow<LoginScreenState>(extraBufferCapacity = 1, onBufferOverflow = DROP_OLDEST)
    val screenState = _screenState.asSharedFlow()

    fun login(login: String, password: CharSequence) {
        viewModelScope.launch {
            sessionFactory.newSession(login, password)
                .onSuccess { mainScreenLauncher.launch() }
                .onFailure {
                    Log.d(LOG_TAG, "Login went wrong", it)
                    _screenState.tryEmit(LoginScreenState.SomethingWentWrong)
                }
        }
    }

    companion object {

        private const val LOG_TAG = "LoginViewModel"
    }
}