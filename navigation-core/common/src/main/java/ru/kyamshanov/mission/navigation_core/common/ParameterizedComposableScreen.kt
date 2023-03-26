package ru.kyamshanov.mission.navigation_core.common

import androidx.compose.runtime.Composable
import ru.kyamshanov.mission.navigation_core.api.Screen

interface ParameterizedComposableScreen : Screen {

    val parameterKeys: List<String>

    val parametersSupplier: () -> Map<String, String?>

    val composableSupplier: @Composable (Map<String, String?>) -> Unit
}