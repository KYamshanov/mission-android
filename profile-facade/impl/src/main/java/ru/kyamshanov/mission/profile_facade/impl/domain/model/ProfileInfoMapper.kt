package ru.kyamshanov.mission.profile_facade.impl.domain.model

import ru.kyamshanov.mission.profile_facade.api.domain.model.ProfileInfo

internal fun ProfileInfoMap.toProfileInfo() = ProfileInfo(
    userId = userId,
    firstname = info[PROFILE_FIRSTNAME] as? String,
    lastname = info[PROFILE_LASTNAME] as? String,
    patronymic = info[PROFILE_PATRONYMIC] as? String,
    group = info[PROFILE_GROUP] as? String,
)