package ru.kyamshanov.mission.session_front.impl.ui.session

import ru.kyamshanov.mission.base_core.api.MissionPreferences
import ru.kyamshanov.mission.session_front.api.UserInfo
import ru.kyamshanov.mission.session_front.api.session.LoggedSession
import ru.kyamshanov.mission.session_front.api.session.UnidentifiedSession
import ru.kyamshanov.mission.session_front.impl.SessionInfoImpl
import ru.kyamshanov.mission.session_front.impl.domain.LoginInteractor
import ru.kyamshanov.mission.session_front.impl.ui.PREFERENCES_ACCESS_KEY
import ru.kyamshanov.mission.session_front.impl.ui.PREFERENCES_REFRESH_KEY

internal class LoggedSessionImpl(
    override val userInfo: UserInfo,
    private val loginInteractor: LoginInteractor,
    private val sessionInfoImpl: SessionInfoImpl,
    private val missionPreferences: MissionPreferences
) : LoggedSession {

    override suspend fun destroySession() {
        missionPreferences.getValue(PREFERENCES_REFRESH_KEY)
            ?.let { loginInteractor.blockRefresh(it) }

        missionPreferences.remove(PREFERENCES_ACCESS_KEY)
        missionPreferences.remove(PREFERENCES_REFRESH_KEY)
        sessionInfoImpl.session = UnidentifiedSession
    }
}