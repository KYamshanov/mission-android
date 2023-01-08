package ru.kyamshanov.mission.navigation_core.common

import androidx.compose.runtime.Composable
import ru.kyamshanov.mission.navigation_core.api.Screen

interface ComposableScreen : Screen {

    val composableSupplier: @Composable () -> Unit
}