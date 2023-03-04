package ru.kyamshanov.mission.session_front.api

import ru.kyamshanov.mission.profile_facade.api.domain.model.ProfileInfo
import ru.kyamshanov.mission.session_front.api.model.UserRole

data class UserInfo(
    val userId: String,
    val login: String,
    val roles: List<UserRole>,
    val profileInfo: ProfileInfo,
)
