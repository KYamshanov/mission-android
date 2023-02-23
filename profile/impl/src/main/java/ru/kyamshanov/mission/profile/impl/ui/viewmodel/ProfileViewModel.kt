package ru.kyamshanov.mission.profile.impl.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import javax.inject.Inject
import kotlinx.coroutines.channels.BufferOverflow.DROP_OLDEST
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.kyamshanov.mission.profile.impl.ui.model.ProfileScreenState
import ru.kyamshanov.mission.session_front.api.SessionFront
import ru.kyamshanov.mission.session_front.api.SessionInfo
import ru.kyamshanov.mission.session_front.api.session.LoggedSession

internal class ProfileViewModel @Inject constructor(
    private val sessionInfo: SessionInfo,
    private val sessionFront: SessionFront,
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
            sessionFront.destroySession()
        }
    }
}