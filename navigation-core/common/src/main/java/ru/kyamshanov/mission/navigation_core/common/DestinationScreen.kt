package ru.kyamshanov.mission.navigation_core.common

import ru.kyamshanov.mission.navigation_core.api.Screen

interface DestinationScreen : Screen {

    val destination: String
        get() = this::class.java.canonicalName as String
}