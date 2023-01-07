package ru.kyamshanov.mission.navigation_core.api

interface Navigator {

    fun navigateTo(screen: Screen)

    fun replaceTo(screen: Screen)
}