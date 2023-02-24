package ru.kyamshanov.mission.android

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.kyamshanov.mission.navigation_core.api.Screen
import ru.kyamshanov.mission.navigation_core.common.ComposableScreen
import ru.kyamshanov.mission.navigation_core.common.DestinationScreen
import ru.kyamshanov.mission.navigation_core.common.ParameterizedComposableScreen
import ru.kyamshanov.mission.project.impl.ui.screen.ProjectScreen

class NavigationRouteFactory {

    fun NavGraphBuilder.createComposable(screen: Screen) {
        val routeDestination = screen.obtainDestination()
        when (screen) {
            is ComposableScreen -> composable(routeDestination) { screen.composableSupplier.invoke() }
            is ProjectScreen -> composable(routeDestination) { backStackEntry ->
                val parameters = screen.parameters.keys.associateWith { parameterKey ->
                    requireNotNull(backStackEntry.arguments?.getString(parameterKey))
                }
                screen.composableSupplier.invoke(parameters)
            }
        }
    }

    private fun Screen.obtainDestination(): String = when (this) {
        is DestinationScreen -> {
            val tail = (this as? ParameterizedComposableScreen)?.getTail().orEmpty()
            destination + tail
        }

        else -> throw IllegalArgumentException()
    }

    private fun ParameterizedComposableScreen.getTail() = buildString {
        parameters.keys.forEach { parameter ->
            append("?$parameter={$parameter}")
        }
    }
}