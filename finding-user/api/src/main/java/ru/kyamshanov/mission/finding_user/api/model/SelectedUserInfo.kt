package ru.kyamshanov.mission.finding_user.api.model

/**
 * Информация о пользователе
 * @property firstName Имя
 * @property lastName Фамилия
 * @property patronymic Отчество
 */
interface SelectedUserInfo {

    val firstName: String
    val lastName: String
    val patronymic: String
}
