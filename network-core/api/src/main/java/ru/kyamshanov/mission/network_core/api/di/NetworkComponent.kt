package ru.kyamshanov.mission.network_core.api.di

import ru.kyamshanov.mission.dagger.CoreComponent
import ru.kyamshanov.mission.network_core.api.RequestFactory

interface NetworkComponent : CoreComponent {

    val requestFactory: RequestFactory
}