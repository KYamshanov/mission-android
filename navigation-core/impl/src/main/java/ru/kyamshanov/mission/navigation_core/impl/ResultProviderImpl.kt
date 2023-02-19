package ru.kyamshanov.mission.navigation_core.impl

import androidx.navigation.NavController
import ru.kyamshanov.mission.navigation_core.api.ResultProvider
import javax.inject.Inject

internal class ResultProviderImpl @Inject constructor(
    private val controller: NavController
) : ResultProvider {

    @Suppress("UNCHECKED_CAST")
    override fun <T> get(key: String, defaultValue: T): T =
        controller.currentBackStackEntry?.savedStateHandle?.remove(key) ?: defaultValue
}