package ru.kyamshanov.mission.finding_user.impl.domain.model

import ru.kyamshanov.mission.finding_user.api.model.SelectedUserInfo
import ru.kyamshanov.mission.navigation_core.common.SerializableNavigationBoundaryData

internal data class UserInfo(
    override val id: String,
    override val name: String?,
    override val age: Int?,
) : SelectedUserInfo, SerializableNavigationBoundaryData
