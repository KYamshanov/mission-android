package ru.kyamshanov.mission.android

import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.kyamshanov.mission.navigation_core.api.Screen
import ru.kyamshanov.mission.navigation_core.common.BOUNDARY_DATA_HOLDER_KEY
import ru.kyamshanov.mission.navigation_core.common.BoundaryDataComposableScreen
import ru.kyamshanov.mission.navigation_core.common.ComposableScreen
import ru.kyamshanov.mission.navigation_core.common.DestinationScreen
import ru.kyamshanov.mission.navigation_core.common.ParameterizedComposableScreen
import ru.kyamshanov.mission.navigation_core.common.SerializableNavigationBoundaryData

class NavigationRouteFactory {

    fun NavGraphBuilder.createComposable(screen: Screen) {
        val routeDestination = screen.obtainDestination()
        when (screen) {
            is ComposableScreen -> composable(routeDestination) {
                println("Compse screen ${screen::class.simpleName}")
                screen.composableSupplier.invoke()
            }
            is ParameterizedComposableScreen -> composable(routeDestination) { backStackEntry ->
                val parameters = screen.parameterKeys.associateWith { parameterKey ->
                    requireNotNull(backStackEntry.arguments?.getString(parameterKey)) { "Parameter $parameterKey was required for screen ${screen::class.simpleName}" }
                }
                screen.composableSupplier.invoke(parameters)
            }
            is BoundaryDataComposableScreen -> composable(routeDestination) { backStackEntry ->
                val parameters: SerializableNavigationBoundaryData = rememberSaveable {
                    val removedBoundaryData: SerializableNavigationBoundaryData? =
                        backStackEntry.savedStateHandle.remove(BOUNDARY_DATA_HOLDER_KEY)
                    requireNotNull(removedBoundaryData) { "Screen ${screen::class.simpleName} cannot be open without data" }
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
        parameterKeys.forEach { parameter ->
            append("?$parameter={$parameter}")
        }
    }
}