package ru.kyamshanov.mission.profile.impl.ui.model

import ru.kyamshanov.mission.session_front.api.model.UserRole

internal data class ProfileScreenState(
    val info: Info?,
    val somethingWentWrong: Boolean,
) {

    constructor() : this(
        info = null,
        somethingWentWrong = false
    )

    constructor(info: Info?) : this(
        info = info,
        somethingWentWrong = false
    )

    data class Info(
        val login: String,
        val firstname: String?,
        val lastname: String?,
        val patronymic: String?,
        val group: String?,
        val roles: List<UserRole>,
    )
}
