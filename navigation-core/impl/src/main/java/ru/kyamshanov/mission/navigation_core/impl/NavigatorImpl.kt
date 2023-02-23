package ru.kyamshanov.mission.navigation_core.impl

import androidx.navigation.NavController
import ru.kyamshanov.mission.navigation_core.api.NavigationBoundaryData
import ru.kyamshanov.mission.navigation_core.api.Navigator
import ru.kyamshanov.mission.navigation_core.api.Screen
import ru.kyamshanov.mission.navigation_core.common.DestinationScreen
import ru.kyamshanov.mission.navigation_core.common.ParameterizedComposableScreen
import javax.inject.Inject

class NavigatorImpl @Inject constructor(
    private val controller: NavController
) : Navigator {

    override fun navigateTo(screen: Screen) {
        when (screen) {
            is DestinationScreen -> controller.navigate(screen.getDestination())
            else -> throw IllegalStateException("Screen implementation isn`t be able to navigate")
        }
    }

    override fun replaceTo(screen: Screen) {
        when (screen) {
            is DestinationScreen -> {
                controller.navigate(screen.getDestination()) {
                    controller.currentDestination?.route?.let {
                        popUpTo(it) { inclusive = true }
                    }
                }
            }
            else -> throw IllegalStateException("Screen implementation isn`t be able to navigate")
        }
    }

    override fun <ReturnDataType : NavigationBoundaryData?> backWithResult(key: String, data: ReturnDataType) {
        controller.popBackStack()
        controller.currentBackStackEntry?.savedStateHandle?.set(key, data)
    }

    private fun Screen.getDestination() = when (this) {
        is DestinationScreen -> this.destination
        else -> throw IllegalStateException("The Screen has not a destination")
    }.let {
        if (this is ParameterizedComposableScreen) it + this.getTail()
        else it
    }

    private fun ParameterizedComposableScreen.getTail() = buildString {
        parameters.forEach { (key, value) ->
            append("?$key=$value")
        }
    }
}