package ru.kyamshanov.mission.session_front.impl.ui.session

import ru.kyamshanov.mission.authentication.di.AuthenticationComponent
import ru.kyamshanov.mission.base_core.api.MissionPreferences
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.session_front.api.UserInfo
import ru.kyamshanov.mission.session_front.api.session.JwtLoggedSession
import ru.kyamshanov.mission.session_front.api.session.UnidentifiedSession
import ru.kyamshanov.mission.session_front.impl.SessionInfoImpl
import ru.kyamshanov.mission.session_front.impl.domain.LoginInteractor
import ru.kyamshanov.mission.session_front.impl.domain.model.AccessStatus.ACTIVE
import ru.kyamshanov.mission.session_front.impl.ui.PREFERENCES_ACCESS_KEY
import ru.kyamshanov.mission.session_front.impl.ui.PREFERENCES_REFRESH_KEY

internal class JwtLoggedSessionImpl(
    override val userInfo: UserInfo,
    override val accessToken: String,
    override val refreshToken: String,
    private val loginInteractor: LoginInteractor,
    private val sessionInfoImpl: SessionInfoImpl,
    private val missionPreferences: MissionPreferences
) : JwtLoggedSession {

    init {
        missionPreferences.saveValue(PREFERENCES_ACCESS_KEY, accessToken)
        missionPreferences.saveValue(PREFERENCES_REFRESH_KEY, refreshToken)
    }

    override suspend fun isActive(): Boolean =
        accessToken.let { loginInteractor.check(it).getOrNull() == ACTIVE }

    override suspend fun destroySession() {
        missionPreferences.getValue(PREFERENCES_REFRESH_KEY)
            ?.let { loginInteractor.blockRefresh(it) }

        missionPreferences.remove(PREFERENCES_ACCESS_KEY)
        missionPreferences.remove(PREFERENCES_REFRESH_KEY)
        sessionInfoImpl.session = UnidentifiedSession

        Di.getComponent<AuthenticationComponent>()?.launcher?.launch()
    }
}