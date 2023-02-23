package ru.kyamshanov.mission.finding_user.api.navigation

/**
 * Ключ для передачи/получения выбранного Пользователя
 *
 * @see [ru.kyamshanov.mission.finding_user.api.model.SelectedUserInfo]
 */
const val SELECTED_USER_EXTRA_KEY = "FindingUser_selectedUser"

interface FindingUserLauncher {

    fun launch()
}