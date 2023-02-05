package ru.kyamshanov.mission.authentication.impl.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow.DROP_OLDEST
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.kyamshanov.mission.authentication.impl.domain.LoginUseCase
import ru.kyamshanov.mission.authentication.impl.ui.model.LoginScreenState
import javax.inject.Inject

internal class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _screenState =
        MutableSharedFlow<LoginScreenState>(extraBufferCapacity = 1, onBufferOverflow = DROP_OLDEST)

    val screenState = _screenState.asSharedFlow()

    fun login(login: String, password: CharSequence) {
        viewModelScope.launch {
            try {
                loginUseCase.login(login, password)
            } catch (e: Exception) {
                _screenState.tryEmit(LoginScreenState.SomethingWentWrong)
            }
        }
    }
}