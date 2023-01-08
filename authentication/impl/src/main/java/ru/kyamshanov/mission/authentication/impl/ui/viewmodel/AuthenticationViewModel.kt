package ru.kyamshanov.mission.authentication.impl.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow.DROP_OLDEST
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.kyamshanov.mission.authentication.impl.ui.model.AuthenticationState
import ru.kyamshanov.mission.main_screen_feature.api.navigation.MainScreenLauncher
import ru.kyamshanov.mission.session_front.api.di.SessionFrontComponent
import ru.kyamshanov.mission.session_front.api.session.LoggedSession

internal class AuthenticationViewModel(
    private val sessionComponent: SessionFrontComponent,
    private val mainScreenLauncher: MainScreenLauncher
) : ViewModel() {

    private val _screenState =
        MutableSharedFlow<AuthenticationState>(replay = 1, onBufferOverflow = DROP_OLDEST)

    val screenState = _screenState.asSharedFlow()

    init {
        viewModelScope.launch {
            if (sessionComponent.sessionInfo is LoggedSession)
                mainScreenLauncher.launch()
            else sessionComponent.sessionFactory.refreshSession()
                .onSuccess {
                    mainScreenLauncher.launch()
                }.onFailure {
                    Log.d(LOG_TAG, "refresh session failed", it)
                    _screenState.tryEmit(AuthenticationState.LOGIN)
                }
        }
    }

    companion object {

        private const val LOG_TAG = "AuthenticationViewModel"
    }
}