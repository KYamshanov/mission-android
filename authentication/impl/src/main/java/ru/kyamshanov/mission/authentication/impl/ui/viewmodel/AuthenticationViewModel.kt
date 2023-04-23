package ru.kyamshanov.mission.authentication.impl.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.kyamshanov.mission.authentication.impl.domain.AuthenticationUseCase
import ru.kyamshanov.mission.authentication.impl.ui.model.AuthenticationState

internal class AuthenticationViewModel @Inject constructor(
    private val authenticationUseCase: AuthenticationUseCase,
) : ViewModel() {

    private val _screenState = MutableStateFlow(AuthenticationState.LOADER)
    val screenState = _screenState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.Default) {
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