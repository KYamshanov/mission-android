package ru.kyamshanov.mission.authentication.impl.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow.DROP_OLDEST
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.kyamshanov.mission.authentication.impl.data.api.AuthenticationApi
import ru.kyamshanov.mission.authentication.impl.ui.model.ScreenState
import ru.kyamshanov.mission.authentication.impl.ui.model.ScreenState.SomethingWentWrong
import ru.kyamshanov.mission.authentication.impl.ui.model.ScreenState.WrongPassword

internal class AuthenticationViewModel(
    private val api: AuthenticationApi
) : ViewModel() {

    private val _screenState = MutableSharedFlow<ScreenState>(extraBufferCapacity = 1, onBufferOverflow = DROP_OLDEST)

    val screenState = _screenState.asSharedFlow()

    init {
        viewModelScope.launch {
            screenState.collect {
                println("_------ $it")
            }
        }
    }

    fun login(login: String, password: CharSequence) {
        viewModelScope.launch {
            api.login(login, password).also {
                println(it)
                _screenState.emit(SomethingWentWrong)
            }
        }
    }
}