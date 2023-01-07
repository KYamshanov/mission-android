package ru.kyamshanov.mission.session_front.impl.domain.session

import ru.kyamshanov.mission.session_front.api.UserInfo
import ru.kyamshanov.mission.session_front.api.session.LoggedSession

internal class LoggedSessionImpl(override val userInfo: UserInfo) : LoggedSession