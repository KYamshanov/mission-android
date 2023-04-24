package ru.kyamshanov.mission.navigation_core.impl

import javax.inject.Inject
import ru.kyamshanov.mission.navigation_core.api.ResultProvider
import ru.kyamshanov.mission.navigation_core.impl.di.NavigatorControllerHolder

internal class ResultProviderImpl @Inject constructor(
    private val navigatorControllerHolder: NavigatorControllerHolder,
) : ResultProvider {

    @Suppress("UNCHECKED_CAST")
    override fun <T> get(key: String, defaultValue: T): T =
        requireNotNull(navigatorControllerHolder.navigator) { "Navigation controller cannot be null" }
            .let { controller ->
                controller.currentBackStackEntry?.savedStateHandle?.remove(key) ?: defaultValue
            }
}