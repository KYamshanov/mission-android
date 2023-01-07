package ru.kyamshanov.mission.navigation_core.api.di

import ru.kyamshanov.mission.dagger.CoreComponent
import ru.kyamshanov.mission.navigation_core.api.Navigator

interface NavigationComponent : CoreComponent {

    val navigator: Navigator
}