package ru.kyamshanov.mission.session_front.api

import ru.kyamshanov.mission.session_front.api.session.Session
import ru.kyamshanov.mission.session_front.api.session.UnidentifiedSession

interface SessionInfo {

    val session : Session
        get() = UnidentifiedSession
}