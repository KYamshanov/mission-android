package ru.kyamshanov.mission.profile.impl.ui.model

import ru.kyamshanov.mission.session_front.api.model.UserRole

internal data class ProfileScreenState(
    val roles: List<UserRole>,
    val age: String,
    val name: String,
)
