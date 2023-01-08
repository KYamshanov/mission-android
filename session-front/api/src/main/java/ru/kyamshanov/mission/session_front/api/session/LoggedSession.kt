package ru.kyamshanov.mission.session_front.api.session

import ru.kyamshanov.mission.session_front.api.UserInfo

interface LoggedSession : Session {

    val userInfo: UserInfo

    suspend fun isActive(): Boolean

    suspend fun destroySession()
}