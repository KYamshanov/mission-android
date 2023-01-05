package ru.kyamshanov.mission.navigation_core.impl

import androidx.navigation.NavController
import ru.kyamshanov.mission.navigation_core.api.Navigator
import ru.kyamshanov.mission.navigation_core.common.DestinationScreen

class NavigatorImpl(
    private val controller: NavController
) : Navigator {

    override fun navigateTo(screen: ru.kyamshanov.mission.navigation_core.api.Screen) {
        when (screen) {
            is DestinationScreen -> controller.navigate(screen.destination)
            else -> throw IllegalStateException("Screen implementation isn`t be able to navigate")
        }
    }
}