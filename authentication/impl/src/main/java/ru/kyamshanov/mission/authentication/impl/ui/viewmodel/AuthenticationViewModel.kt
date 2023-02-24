package ru.kyamshanov.mission.authentication.impl.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow.DROP_OLDEST
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.kyamshanov.mission.authentication.impl.domain.AuthenticationUseCase
import ru.kyamshanov.mission.authentication.impl.ui.model.AuthenticationState

internal class AuthenticationViewModel @Inject constructor(
    private val authenticationUseCase: AuthenticationUseCase,
) : ViewModel() {

    private val _screenState =
        MutableSharedFlow<AuthenticationState>(replay = 1, onBufferOverflow = DROP_OLDEST)

    val screenState = _screenState.asSharedFlow()

    init {
        viewModelScope.launch(Dispatchers.Main) {
            authenticationUseCase.obtainSession()
                .onFailure { e ->
                    Log.e(LOG_TAG, "obtainSession error", e)
                    _screenState.tryEmit(AuthenticationState.LOGIN)
                }
        }
    }

    private companion object {

        const val LOG_TAG = "AuthenticationViewModel"
    }
}