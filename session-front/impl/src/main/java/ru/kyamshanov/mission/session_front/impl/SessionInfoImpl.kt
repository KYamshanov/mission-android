package ru.kyamshanov.mission.session_front.impl

import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.session_front.api.SessionInfo
import ru.kyamshanov.mission.session_front.api.session.Session
import javax.inject.Inject

@ComponentItem
internal class SessionInfoImpl @Inject constructor() : SessionInfo {

    override var session: Session = super.session
}