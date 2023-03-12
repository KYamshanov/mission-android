package ru.kyamshanov.mission.navigation_core.impl

import androidx.navigation.NavController
import javax.inject.Inject
import ru.kyamshanov.mission.navigation_core.api.NavigationBoundaryData
import ru.kyamshanov.mission.navigation_core.api.Navigator
import ru.kyamshanov.mission.navigation_core.api.Screen
import ru.kyamshanov.mission.navigation_core.common.BOUNDARY_DATA_HOLDER_KEY
import ru.kyamshanov.mission.navigation_core.common.BoundaryDataComposableScreen
import ru.kyamshanov.mission.navigation_core.common.DestinationScreen
import ru.kyamshanov.mission.navigation_core.common.ParameterizedComposableScreen

class NavigatorImpl @Inject constructor(
    private val controller: NavController,
) : Navigator {

    override fun navigateTo(screen: Screen) {
        when (screen) {
            is DestinationScreen -> controller.navigate(screen.getDestination())
            else -> throw IllegalStateException("Screen implementation isn`t be able to navigate")
        }
        if (screen is BoundaryDataComposableScreen) {
            controller.currentBackStackEntry?.savedStateHandle?.set(BOUNDARY_DATA_HOLDER_KEY, screen.boundaryData)
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
        if (screen is BoundaryDataComposableScreen) {
            controller.currentBackStackEntry?.savedStateHandle?.set(BOUNDARY_DATA_HOLDER_KEY, screen.boundaryData)
        }
    }

    override fun exit() {
        controller.navigateUp()
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