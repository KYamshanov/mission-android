package ru.kyamshanov.mission.authentication.impl.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow.DROP_OLDEST
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.kyamshanov.mission.authentication.impl.domain.AuthenticationUseCase
import ru.kyamshanov.mission.authentication.impl.ui.model.AuthenticationState
import javax.inject.Inject

internal class AuthenticationViewModel @Inject constructor(
    private val authenticationUseCase: AuthenticationUseCase
) : ViewModel() {

    private val _screenState =
        MutableSharedFlow<AuthenticationState>(replay = 1, onBufferOverflow = DROP_OLDEST)

    val screenState = _screenState.asSharedFlow()

    init {
        viewModelScope.launch {
            try {
                authenticationUseCase.obtainSession()
            } catch (e: Exception) {
                _screenState.tryEmit(AuthenticationState.LOGIN)
            }
        }
    }
}