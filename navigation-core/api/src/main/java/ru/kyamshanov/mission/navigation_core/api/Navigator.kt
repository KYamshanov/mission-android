package ru.kyamshanov.mission.navigation_core.api

interface Navigator {

    fun navigateTo(screen: Screen)

    fun replaceTo(screen: Screen)

    /**
     * @see [ru.kyamshanov.mission.navigation_core.common.SerializableNavigationBoundaryData]
     */
    fun <ReturnDataType : NavigationBoundaryData?> backWithResult(key: String, data: ReturnDataType)
}