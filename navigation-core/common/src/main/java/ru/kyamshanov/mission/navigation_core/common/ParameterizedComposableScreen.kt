package ru.kyamshanov.mission.navigation_core.common

import androidx.compose.runtime.Composable
import ru.kyamshanov.mission.navigation_core.api.Screen

interface ParameterizedComposableScreen : Screen {

    val parameters: Array<String>

    val composableSupplier: @Composable (parameters: Map<String, String>) -> Unit
}