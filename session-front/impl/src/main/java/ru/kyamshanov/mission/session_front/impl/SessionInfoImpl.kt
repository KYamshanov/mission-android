package ru.kyamshanov.mission.session_front.impl

import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.session_front.api.SessionInfo
import ru.kyamshanov.mission.session_front.api.model.UserRole
import ru.kyamshanov.mission.session_front.api.session.LoggedSession
import ru.kyamshanov.mission.session_front.api.session.Session
import ru.kyamshanov.mission.session_front.api.session.UnidentifiedSession

@ComponentItem
internal class SessionInfoImpl @Inject constructor() : SessionInfo {

    private val _sessionState = MutableStateFlow<Session>(UnidentifiedSession)

    override val sessionState: StateFlow<Session> = _sessionState.asStateFlow()

    override var session: Session
        get() = super.session
        set(value) {
            _sessionState.value = value
        }

    override fun hasRole(role: UserRole): Boolean =
        (session as? LoggedSession)?.userInfo?.roles?.contains(role) == true
}