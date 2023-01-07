package ru.kyamshanov.mission.base_core.api.di

import ru.kyamshanov.mission.base_core.api.Device
import ru.kyamshanov.mission.base_core.api.MissionPreferences
import ru.kyamshanov.mission.dagger.CoreComponent

interface BaseCoreComponent : CoreComponent {

    val missionPreferences: MissionPreferences

    val device: Device
}