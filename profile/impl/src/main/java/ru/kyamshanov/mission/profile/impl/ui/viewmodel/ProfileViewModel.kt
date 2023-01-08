package ru.kyamshanov.mission.profile.impl.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow.DROP_OLDEST
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.kyamshanov.mission.profile.impl.ui.model.ProfileScreenState
import ru.kyamshanov.mission.session_front.api.SessionInfo
import ru.kyamshanov.mission.session_front.api.session.LoggedSession
import javax.inject.Inject

internal class ProfileViewModel @Inject constructor(
    private val sessionInfo: SessionInfo
) : ViewModel() {

    private val _screenState = MutableSharedFlow<ProfileScreenState>(replay = 1, onBufferOverflow = DROP_OLDEST)

    val screenState = _screenState.asSharedFlow()

    init {
        viewModelScope.launch {
            (sessionInfo.session as? LoggedSession)?.userInfo?.run {
                ProfileScreenState(
                    roles = roles
                )
            }?.also { _screenState.emit(it) }
        }
    }

    fun exit() {
        viewModelScope.launch {
            val session = sessionInfo.session
            if (session is LoggedSession) {
                session.destroySession()
            }
        }
    }
}