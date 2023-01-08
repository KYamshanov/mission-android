package ru.kyamshanov.mission.profile.impl.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kyamshanov.mission.authentication.AuthenticationLauncher
import ru.kyamshanov.mission.session_front.api.SessionInfo
import ru.kyamshanov.mission.session_front.api.session.LoggedSession
import javax.inject.Inject

internal class ProfileViewModel @Inject constructor(
    private val sessionInfo: SessionInfo,
    private val authenticationLauncher: AuthenticationLauncher
) : ViewModel() {

    fun exit() {
        viewModelScope.launch {
            val session = sessionInfo.session
            if (session is LoggedSession) {
                session.destroySession()
                authenticationLauncher.launch()
            }
        }
    }
}