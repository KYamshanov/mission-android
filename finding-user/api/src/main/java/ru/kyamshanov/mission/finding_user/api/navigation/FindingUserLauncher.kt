package ru.kyamshanov.mission.finding_user.api.navigation

import ru.kyamshanov.mission.finding_user.api.model.SearchStrategy
import ru.kyamshanov.mission.finding_user.api.model.SelectedUserInfo

/**
 * Ключ для передачи/получения выбранного Пользователя
 *
 * @see [ru.kyamshanov.mission.finding_user.api.model.SelectedUserInfo]
 */
const val SELECTED_USER_EXTRA_KEY = "FindingUser_selectedUser"

interface FindingUserLauncher {

    fun launch()

    fun launch(searchStrategy: SearchStrategy)

    suspend fun suspendLaunch(searchStrategy: SearchStrategy = SearchStrategy.All): SelectedUserInfo?
}