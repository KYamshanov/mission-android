package ru.kyamshanov.mission.profile_facade.impl.domain.model

import ru.kyamshanov.mission.profile_facade.api.domain.model.ProfileInfo

internal fun ProfileInfoMap.toProfileInfo() = ProfileInfo(
    userId = userId,
    age = info[PROFILE_AGE_KEY] as? Int,
    name = info[PROFILE_NAME_KEY] as? String
)