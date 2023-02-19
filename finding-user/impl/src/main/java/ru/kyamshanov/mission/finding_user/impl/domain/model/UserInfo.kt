package ru.kyamshanov.mission.finding_user.impl.domain.model

import ru.kyamshanov.mission.finding_user.api.model.SelectedUserInfo
import ru.kyamshanov.mission.navigation_core.common.SerializableNavigationBoundaryData

/**
 * Информация о пользователе
 * @property firstName Имя
 * @property lastName Фамилия
 * @property patronymic Отчество
 */
internal data class UserInfo(
    override val firstName: String,
    override val lastName: String,
    override val patronymic: String
) : SelectedUserInfo, SerializableNavigationBoundaryData
