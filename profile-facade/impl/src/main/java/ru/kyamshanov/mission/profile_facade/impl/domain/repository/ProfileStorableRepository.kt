package ru.kyamshanov.mission.profile_facade.impl.domain.repository

import ru.kyamshanov.mission.profile_facade.impl.domain.model.ProfileInfoMap

internal interface ProfileStorableRepository {

    suspend fun fetchProfile(
        refresh: Boolean,
    ): ProfileInfoMap
}