package ru.kyamshanov.mission.session_front.impl.ui.session

import ru.kyamshanov.mission.session_front.api.UserInfo
import ru.kyamshanov.mission.session_front.api.session.JwtLoggedSession
import ru.kyamshanov.mission.session_front.impl.domain.LoginInteractor
import ru.kyamshanov.mission.session_front.impl.domain.model.AccessStatus.ACTIVE

internal class JwtLoggedSessionImpl(
    override val userInfo: UserInfo,
    override val accessToken: String,
    override val refreshToken: String,
    private val loginInteractor: LoginInteractor,
) : JwtLoggedSession {

    override suspend fun isActive(): Boolean =
        accessToken.let { loginInteractor.check(it).getOrNull() == ACTIVE }
}