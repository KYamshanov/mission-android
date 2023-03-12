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
        val roles: List<UserRole>,
        val age: String,
        val name: String,
    )
}
